package com.zr;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

import com.zr.helper.BGHelper;

import static android.os.Build.VERSION_CODES.JELLY_BEAN_MR1;

public class ImageViewAdapt extends AppCompatImageView implements  LayoutAdaptHelper.AdaptView {
    private LayoutAdaptHelper mHelper = new LayoutAdaptHelper();

    public ImageViewAdapt(Context context) {
        super(context);
        mHelper.init(this, null, R.attr.LayoutAdaptAttr, R.style.LayoutAdaptStyle);
    }

    public ImageViewAdapt(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHelper.init(this, attrs, R.attr.LayoutAdaptAttr, R.style.LayoutAdaptStyle);
    }

    public ImageViewAdapt(Context context, AttributeSet attrs, int defStyleAttr) {
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
        BGHelper.resetDrawable(this,preDrawable);
        BGHelper.resetImageDrawable(this,preImageDrawable);
    }

    private Drawable preDrawable;
    /*因为setBackground在构造函数的super方法中先于LayoutAdaptHelper构造之前执行，所以先保存Drawable*/
    @Override
    public void setBackgroundDrawable(Drawable background) {
        if(BGHelper.drawableAdaptEnable(mHelper)){
            Drawable drawable = BGHelper.drawableAdapt(this, background, mHelper);
            super.setBackgroundDrawable(drawable);
            preDrawable=null;
        }else{
            preDrawable=background;
            super.setBackgroundDrawable(background);
        }
    }

    private Drawable preImageDrawable;
    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        if(BGHelper.drawableAdaptEnable(mHelper)){
            Drawable d = BGHelper.drawableAdapt(this, drawable, mHelper);
            super.setImageDrawable(d);
            preImageDrawable=null;
        }else{
            preImageDrawable=drawable;
            super.setImageDrawable(drawable);
        }
    }
}
