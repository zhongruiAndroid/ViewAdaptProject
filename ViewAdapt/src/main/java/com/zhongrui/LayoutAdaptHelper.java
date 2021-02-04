package com.zhongrui;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

public class LayoutAdaptHelper {
    public interface AdaptView {

    }

    public interface LayoutAdaptParams {
        LayoutParamsInfo getLayoutAdaptInfo();
    }

    public int uiDesignWidth;
    public int uiDesignHeight;
    public boolean uiAdaptWidth;
    public boolean uiAdaptEnable;

    public int selfWidth;
    public int selfHeight;

    public void obtainStyledAttributes(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.zhongruiAdapt, defStyleAttr, defStyleRes);
        uiDesignWidth = typedArray.getInt(R.styleable.zhongruiAdapt_uiDesignWidth, 0);
        uiDesignHeight = typedArray.getInt(R.styleable.zhongruiAdapt_uiDesignHeight, 0);
        uiAdaptWidth = typedArray.getBoolean(R.styleable.zhongruiAdapt_uiAdaptWidth, true);
        uiAdaptEnable = typedArray.getBoolean(R.styleable.zhongruiAdapt_uiAdaptEnable, true);

        selfWidth = typedArray.getDimensionPixelOffset(R.styleable.zhongruiAdapt_layout_widthAdapt, -1);
        selfHeight = typedArray.getDimensionPixelOffset(R.styleable.zhongruiAdapt_layout_heightAdapt, -1);
        typedArray.recycle();
    }

    public boolean canUseAdapt() {
        if (!uiAdaptEnable) {
            return false;
        }
        if (uiAdaptWidth) {
            if (uiDesignWidth > 0) {
                return true;
            }
        } else {
            if (uiDesignHeight > 0) {
                return true;
            }
        }
        return false;
    }

    private final ViewGroup mHost;

    public LayoutAdaptHelper(@NonNull ViewGroup host) {
        if (host == null) {
            throw new IllegalArgumentException("host must be non-null");
        }
        mHost = host;
    }

    public void adjustChildren() {
        for (int i = 0, N = mHost.getChildCount(); i < N; i++) {
            View view = mHost.getChildAt(i);
            ViewGroup.LayoutParams params = view.getLayoutParams();
            if (params instanceof LayoutAdaptHelper.LayoutAdaptParams) {
                LayoutParamsInfo layoutAdaptInfo = ((LayoutAdaptParams) params).getLayoutAdaptInfo();
                if (params instanceof ViewGroup.MarginLayoutParams) {
                    fillMarginLayoutParams(layoutAdaptInfo, view, (ViewGroup.MarginLayoutParams) params);
                } else {
                    fillLayoutParams(layoutAdaptInfo, view, params);
                }
            }
        }
    }

    public static int getScreenWidth(View view) {
        return view.getContext().getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(View view) {
        return view.getContext().getResources().getDisplayMetrics().heightPixels;
    }

    public static int getStatusBarHeight() {
        return 1;
    }


    public void fillMarginLayoutParams(LayoutParamsInfo layoutAdaptInfo, View view, ViewGroup.MarginLayoutParams params) {
        if (!canUseAdapt()) {
            return;
        }
        fillLayoutParams(layoutAdaptInfo, view, params);
        if (layoutAdaptInfo.leftMarginAdapt > 0) {
            params.leftMargin = getRealSizeInt(view, layoutAdaptInfo.leftMarginAdapt);
        }
        if (layoutAdaptInfo.topMarginAdapt > 0) {
            params.topMargin = getRealSizeInt(view, layoutAdaptInfo.topMarginAdapt);
        }
        if (layoutAdaptInfo.rightMarginAdapt > 0) {
            params.rightMargin = getRealSizeInt(view, layoutAdaptInfo.rightMarginAdapt);
        }
        if (layoutAdaptInfo.bottomMarginAdapt > 0) {
            params.bottomMargin = getRealSizeInt(view, layoutAdaptInfo.bottomMarginAdapt);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (layoutAdaptInfo.startMarginAdapt > 0) {
                MarginLayoutParamsCompat.setMarginStart(params, getRealSizeInt(view, layoutAdaptInfo.startMarginAdapt));
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (layoutAdaptInfo.endMarginAdapt > 0) {
                MarginLayoutParamsCompat.setMarginEnd(params, getRealSizeInt(view, layoutAdaptInfo.endMarginAdapt));
            }
        }
    }

    public void fillLayoutParams(LayoutParamsInfo layoutAdaptInfo, View view, ViewGroup.LayoutParams params) {
        if (!canUseAdapt()) {
            return;
        }
        if (layoutAdaptInfo.widthAdapt > 0) {
            params.width = (int) getRealSize(view, layoutAdaptInfo.widthAdapt);
        }
        if (layoutAdaptInfo.heightAdapt > 0) {
            params.height = (int) getRealSize(view, layoutAdaptInfo.heightAdapt);
        }
    }

    /*获取适配ui设计稿的宽度或者高度*/
    private float getReferenceUISize() {
        float tempUiSize = uiDesignWidth;
        if (!uiAdaptWidth) {
            tempUiSize = uiDesignHeight;
        }
        return tempUiSize;
    }

    /*根据设计稿上面view宽高的标注换算成实际屏幕绘制的宽高*/
    public float getRealSize(View view, int uiSize) {
        float screenSize = uiAdaptWidth ? LayoutAdaptHelper.getScreenWidth(view) : LayoutAdaptHelper.getScreenHeight(view);
        float resultSize = screenSize * uiSize / getReferenceUISize();
        return resultSize;
    }

    public int getRealSizeInt(View view, int uiSize) {
        return (int) getRealSize(view, uiSize);
    }


}
