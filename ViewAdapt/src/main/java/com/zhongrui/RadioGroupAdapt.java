package com.zhongrui;

import android.content.Context;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioGroup;

import static android.os.Build.VERSION_CODES.JELLY_BEAN_MR1;

public class RadioGroupAdapt extends RadioGroup implements LayoutAdaptHelper.AdaptView {
    private LayoutAdaptHelper mHelper = new LayoutAdaptHelper();

    public RadioGroupAdapt(Context context) {
        super(context);
        mHelper.init(this, null, R.attr.LayoutAdaptAttr, R.style.LayoutAdaptStyle);
    }

    public RadioGroupAdapt(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHelper.init(this, attrs, R.attr.LayoutAdaptAttr, R.style.LayoutAdaptStyle);
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
}