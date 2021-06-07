package com.zr;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

public class LayoutParamsInfo {
    public int widthAdapt;
    public int heightAdapt;

    public int leftMarginAdapt;
    public int topMarginAdapt;
    public int rightMarginAdapt;
    public int bottomMarginAdapt;
    public int startMarginAdapt;
    public int endMarginAdapt;

    public void getAttributeSet(Context c, AttributeSet attrs) {
        TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.LayoutAdapt_Layout);
        widthAdapt = a.getDimensionPixelSize(R.styleable.LayoutAdapt_Layout_layout_adapt_width, 0);
        heightAdapt = a.getDimensionPixelSize(R.styleable.LayoutAdapt_Layout_layout_adapt_height, 0);

        int margin = a.getDimensionPixelSize(R.styleable.LayoutAdapt_Layout_layout_adapt_margin, -1);
        if (margin >= 0) {
            leftMarginAdapt = margin;
            topMarginAdapt = margin;
            rightMarginAdapt = margin;
            bottomMarginAdapt = margin;
        } else {
            int horizontalMargin = a.getDimensionPixelSize(R.styleable.LayoutAdapt_Layout_layout_adapt_marginStart, -1);
            int verticalMargin = a.getDimensionPixelSize(R.styleable.LayoutAdapt_Layout_layout_adapt_marginEnd, -1);
            if (horizontalMargin >= 0) {
                leftMarginAdapt = horizontalMargin;
                rightMarginAdapt = horizontalMargin;
                startMarginAdapt = horizontalMargin;
            } else {
                leftMarginAdapt = a.getDimensionPixelSize(R.styleable.LayoutAdapt_Layout_layout_adapt_marginLeft, 0);
                rightMarginAdapt = a.getDimensionPixelSize(R.styleable.LayoutAdapt_Layout_layout_adapt_marginRight, 0);
            }
            if (verticalMargin >= 0) {
                topMarginAdapt = verticalMargin;
                bottomMarginAdapt = verticalMargin;
                endMarginAdapt = verticalMargin;
            } else {
                topMarginAdapt = a.getDimensionPixelSize(R.styleable.LayoutAdapt_Layout_layout_adapt_marginTop, 0);
                bottomMarginAdapt = a.getDimensionPixelSize(R.styleable.LayoutAdapt_Layout_layout_adapt_marginBottom, 0);
            }
        }
        a.recycle();
    }
}
