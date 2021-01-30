package com.zhongrui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

public class AdaptConfig {
    public int uiDesignWidth;
    public int uiDesignHeight;
    public boolean uiAdaptUseWidth;
    public boolean uiAdaptEnable;

    public void obtainStyledAttributes(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.zhongruiAdapt, defStyleAttr, defStyleRes);
        uiDesignWidth = typedArray.getInt(R.styleable.zhongruiAdapt_uiDesignWidth, 1080);
        uiDesignHeight = typedArray.getInt(R.styleable.zhongruiAdapt_uiDesignHeight, 2160);
        uiAdaptUseWidth = typedArray.getBoolean(R.styleable.zhongruiAdapt_uiAdaptUseWidth, true);
        uiAdaptEnable = typedArray.getBoolean(R.styleable.zhongruiAdapt_uiAdaptEnable, true);
        typedArray.recycle();
    }
}
