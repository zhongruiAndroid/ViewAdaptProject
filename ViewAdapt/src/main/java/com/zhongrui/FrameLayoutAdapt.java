package com.zhongrui;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

public class FrameLayoutAdapt extends FrameLayout {
    private AdaptConfig adaptConfig;
    /*需要判断状态栏是否隐藏*/
    public FrameLayoutAdapt(@NonNull Context context) {
        super(context);
        init(null, R.attr.LayoutAdaptAttr, R.style.LayoutAdaptStyle);
    }

    public FrameLayoutAdapt(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs, R.attr.LayoutAdaptAttr, R.style.LayoutAdaptStyle);
    }

    public FrameLayoutAdapt(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr, R.style.LayoutAdaptStyle);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FrameLayoutAdapt(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, defStyleAttr, defStyleRes);
    }
    private void init(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        adaptConfig =new AdaptConfig();
        adaptConfig.obtainStyledAttributes(getContext(),attrs,defStyleAttr,defStyleRes);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i("=====", "=====onLayout");
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        super.setPadding(left, top, right, bottom);
    }

    @Override
    public void setPaddingRelative(int start, int top, int end, int bottom) {
        super.setPaddingRelative(start, top, end, bottom);
    }

    public void setMinimumHeightUseAdapt(int minHeight) {
        super.setMinimumHeight(minHeight);
    }

    public void setMinimumWidthUseAdapt(int minWidth) {
        super.setMinimumWidth(minWidth);
    }
}
