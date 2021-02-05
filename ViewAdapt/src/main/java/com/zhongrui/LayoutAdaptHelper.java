package com.zhongrui;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.os.Build.VERSION_CODES.JELLY_BEAN_MR1;

public class LayoutAdaptHelper {
    public interface AdaptLayout {
        void setPaddingAdapt(int left, int top, int right, int bottom);

        @RequiresApi(api = JELLY_BEAN_MR1)
        void setPaddingRelativeAdapt(int start, int top, int end, int bottom);
    }

    public interface AdaptView extends AdaptLayout {
        void setUiDesign(View view, int uiWidth, int uiHeight, boolean useAdaptWidth, boolean adaptEnable);
    }

    public interface AdaptSize {
        void setTextSize(int unit, float size);

        void setAutoSizeTextTypeUniformWithConfiguration(int autoSizeMinTextSize, int autoSizeMaxTextSize, int autoSizeStepGranularity, int unit);
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

    public int adapt_padding;
    public int adapt_paddingHorizontal;
    public int adapt_paddingVertical;
    public int adapt_paddingLeft;
    public int adapt_paddingTop;
    public int adapt_paddingRight;
    public int adapt_paddingBottom;
    public int adapt_paddingStart;
    public int adapt_paddingEnd;


    public int adapt_textSize;
    public int adapt_autoSizeStepGranularity;
    public int adapt_autoSizeMaxTextSize;
    public int adapt_autoSizeMinTextSize;


    public void init(View view, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        if (view == null) {
            return;
        }
        if (view instanceof ViewGroup) {
            obtainStyledAttributes(view, attrs, defStyleAttr, defStyleRes);
        } else {
            obtainStyledAttributesForView(view, attrs, defStyleAttr, defStyleRes);
        }
    }

    private void obtainStyledAttributes(View view, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        if (view == null) {
            return;
        }
        TypedArray typedArray = view.getContext().obtainStyledAttributes(attrs, R.styleable.zhongruiAdapt, defStyleAttr, defStyleRes);
        uiDesignWidth = typedArray.getInt(R.styleable.zhongruiAdapt_uiDesignWidth, 0);
        uiDesignHeight = typedArray.getInt(R.styleable.zhongruiAdapt_uiDesignHeight, 0);
        uiAdaptWidth = typedArray.getBoolean(R.styleable.zhongruiAdapt_uiAdaptWidth, true);
        uiAdaptEnable = typedArray.getBoolean(R.styleable.zhongruiAdapt_uiAdaptEnable, true);

        selfWidth = typedArray.getDimensionPixelOffset(R.styleable.zhongruiAdapt_layout_adapt_width, -1);
        selfHeight = typedArray.getDimensionPixelOffset(R.styleable.zhongruiAdapt_layout_adapt_height, -1);

        obtainStyledAttributesForView(view, typedArray);

        typedArray.recycle();


    }

    private void obtainStyledAttributesForView(View view, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        if (view == null) {
            return;
        }
        TypedArray typedArray = view.getContext().obtainStyledAttributes(attrs, R.styleable.zhongruiAdapt, defStyleAttr, defStyleRes);
        obtainStyledAttributesForView(view, typedArray);
        typedArray.recycle();
    }

    public void setUiDesign(View view, int uiWidth, int uiHeight, boolean useAdaptWidth, boolean adaptEnable) {
        uiDesignWidth = uiWidth;
        uiDesignHeight = uiHeight;
        uiAdaptWidth = useAdaptWidth;
        uiAdaptEnable = adaptEnable;
        if (canUseAdapt()) {
            setPaddingAdapt(view);
            setSizeAdapt(view);
        }
    }

    private void setSizeAdapt(View view) {
        if (canUseAdapt()) {
            /*有可能父view还没有传给子viwui尺寸*/
            return;
        }
        if (adapt_textSize >= 0) {
            ((AdaptSize) view).setTextSize(TypedValue.COMPLEX_UNIT_PX, adapt_textSize == 0 ? 0 : getRealSize(view, adapt_textSize));
        }
        if (adapt_autoSizeMaxTextSize > 0 && adapt_autoSizeMinTextSize > 0 && adapt_autoSizeStepGranularity > 0) {
            int min = getRealSizeInt(view, adapt_autoSizeMinTextSize);
            int max = getRealSizeInt(view, adapt_autoSizeMaxTextSize);
            int step = getRealSizeInt(view, adapt_autoSizeStepGranularity);
            ((AdaptSize) view).setAutoSizeTextTypeUniformWithConfiguration(min, max, step, TypedValue.COMPLEX_UNIT_PX);
        }
    }

    public void obtainStyledAttributesForView(View view, TypedArray typedArray) {
        if (view instanceof AdaptLayout) {
            adapt_padding = typedArray.getDimensionPixelOffset(R.styleable.zhongruiAdapt_adapt_padding, -1);
            if (adapt_padding >= 0) {
                adapt_paddingHorizontal = adapt_padding;
                adapt_paddingVertical = adapt_padding;
                adapt_paddingLeft = adapt_padding;
                adapt_paddingTop = adapt_padding;
                adapt_paddingRight = adapt_padding;
                adapt_paddingBottom = adapt_padding;

                if (Build.VERSION.SDK_INT >= JELLY_BEAN_MR1) {
                    adapt_paddingStart = adapt_padding;
                    adapt_paddingEnd = adapt_padding;
                }
            } else {
                adapt_paddingHorizontal = typedArray.getDimensionPixelOffset(R.styleable.zhongruiAdapt_adapt_paddingHorizontal, 0);
                adapt_paddingVertical = typedArray.getDimensionPixelOffset(R.styleable.zhongruiAdapt_adapt_paddingVertical, 0);
                adapt_paddingLeft = typedArray.getDimensionPixelOffset(R.styleable.zhongruiAdapt_adapt_paddingLeft, adapt_paddingHorizontal);
                adapt_paddingTop = typedArray.getDimensionPixelOffset(R.styleable.zhongruiAdapt_adapt_paddingTop, adapt_paddingVertical);
                adapt_paddingRight = typedArray.getDimensionPixelOffset(R.styleable.zhongruiAdapt_adapt_paddingRight, adapt_paddingHorizontal);
                adapt_paddingBottom = typedArray.getDimensionPixelOffset(R.styleable.zhongruiAdapt_adapt_paddingBottom, adapt_paddingVertical);

            }

            if (Build.VERSION.SDK_INT >= JELLY_BEAN_MR1) {
                adapt_paddingStart = typedArray.getDimensionPixelOffset(R.styleable.zhongruiAdapt_adapt_paddingStart, -1);
                adapt_paddingEnd = typedArray.getDimensionPixelOffset(R.styleable.zhongruiAdapt_adapt_paddingEnd, -1);

                if (adapt_paddingStart >= 0) {
                    adapt_paddingLeft = adapt_paddingStart;
                }
                if (adapt_paddingEnd >= 0) {
                    adapt_paddingRight = adapt_paddingEnd;
                }
            }
            setPaddingAdapt(view);

        }
        if (view instanceof AdaptSize) {
            adapt_textSize = typedArray.getDimensionPixelOffset(R.styleable.zhongruiAdapt_adapt_textSize, -1);
            adapt_autoSizeMaxTextSize = typedArray.getDimensionPixelOffset(R.styleable.zhongruiAdapt_adapt_autoSizeMaxTextSize, -1);
            adapt_autoSizeMinTextSize = typedArray.getDimensionPixelOffset(R.styleable.zhongruiAdapt_adapt_autoSizeMinTextSize, -1);
            adapt_autoSizeStepGranularity = typedArray.getDimensionPixelOffset(R.styleable.zhongruiAdapt_adapt_autoSizeStepGranularity, -1);
            setSizeAdapt(view);
        }
    }

    private void setPaddingAdapt(View view) {
        if (Build.VERSION.SDK_INT >= JELLY_BEAN_MR1) {
            ((AdaptLayout) view).setPaddingRelativeAdapt(adapt_paddingLeft, adapt_paddingTop, adapt_paddingRight, adapt_paddingBottom);
        } else {
            ((AdaptLayout) view).setPaddingAdapt(adapt_paddingLeft, adapt_paddingTop, adapt_paddingRight, adapt_paddingBottom);
        }
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

    public void adjustChildren(ViewGroup mHost) {
        if (mHost == null) {
            throw new IllegalArgumentException("host must be non-null");
        }
        for (int i = 0, N = mHost.getChildCount(); i < N; i++) {
            View view = mHost.getChildAt(i);
            if (view instanceof LayoutAdaptHelper.AdaptView) {
                /*将viewgroup设置的ui设计尺寸传给子view*/
                /*view需要适配padding*/
                ((LayoutAdaptHelper.AdaptView) view).setUiDesign(view, uiDesignWidth, uiDesignHeight, uiAdaptWidth, uiAdaptEnable);
            }
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
        float referenceUISize = getReferenceUISize();
        if (referenceUISize == 0) {
            return uiSize;
        }
        float screenSize = uiAdaptWidth ? LayoutAdaptHelper.getScreenWidth(view) : LayoutAdaptHelper.getScreenHeight(view);
        float resultSize = screenSize * uiSize / referenceUISize;
        return resultSize;
    }

    public int getRealSizeInt(View view, int uiSize) {
        return (int) getRealSize(view, uiSize);
    }


}
