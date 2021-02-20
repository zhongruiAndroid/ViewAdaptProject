package com.zhongrui;

import android.content.Context;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import android.view.View;

import static android.os.Build.VERSION_CODES.JELLY_BEAN_MR1;

public class RadioButtonAdapt extends AppCompatRadioButton implements  LayoutAdaptHelper.AdaptView , LayoutAdaptHelper.AdaptSize {
    private LayoutAdaptHelper mHelper = new LayoutAdaptHelper();

    public RadioButtonAdapt(Context context) {
        super(context);
        mHelper.init(this, null, R.attr.LayoutAdaptAttr, R.style.LayoutAdaptStyle);
    }

    public RadioButtonAdapt(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHelper.init(this, attrs, R.attr.LayoutAdaptAttr, R.style.LayoutAdaptStyle);
    }

    public RadioButtonAdapt(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHelper.init(this, attrs, defStyleAttr, R.style.LayoutAdaptStyle);
    }

    public void setPaddingAdapt(int left, int top, int right, int bottom) {
        if (mHelper.canUseAdapt()) {
            super.setPadding(mHelper.getRealSizeInt(this, left), mHelper.getRealSizeInt(this, top), mHelper.getRealSizeInt(this, right), mHelper.getRealSizeInt(this, bottom));
        }
    }

    @RequiresApi(api = JELLY_BEAN_MR1)
    public void setPaddingRelativeAdapt(int start, int top, int end, int bottom) {
        if (mHelper.canUseAdapt()) {
            super.setPaddingRelative(mHelper.getRealSizeInt(this, start), mHelper.getRealSizeInt(this, top), mHelper.getRealSizeInt(this, end), mHelper.getRealSizeInt(this, bottom));
        }
    }
    @Override
    public void setUiDesign(View view, int uiWidth, int uiHeight, boolean useAdaptWidth, boolean adaptEnable) {
        mHelper.setUiDesign(view, uiWidth, uiHeight, useAdaptWidth, adaptEnable);
    }

    @Override
    public void ignoreWidth(int ignoreWidth) {
        mHelper.setIgnoreAdaptWidth(ignoreWidth);
    }

    @Override
    public void ignoreHeight(int ignoreHeight) {
        mHelper.setIgnoreAdaptHeight(ignoreHeight);
    }

    @Override
    public void setContentViewSize(int width, int height) {
        mHelper.setContentViewSize(width, height);
    }
}
