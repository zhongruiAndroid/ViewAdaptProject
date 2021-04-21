package com.test.viewadapt;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class CustomTextView extends AppCompatTextView {

    private boolean noSpace = true;
    private Paint.FontMetrics fontMetrics;
    private Rect textRect;

    public CustomTextView(Context context) {
        super(context);
        init();
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (noSpace) {
            if (fontMetrics == null) {
                fontMetrics = new Paint.FontMetrics();
            }
            getPaint().getFontMetrics(fontMetrics);

            if (textRect == null) {
                textRect = new Rect();
            }
            getPaint().getTextBounds(getText().toString(), 0, getText().length(), textRect);


            int top = (int) (fontMetrics.top - textRect.top);
            int bottom = (int) (fontMetrics.bottom - textRect.bottom);
            int paddingTop = getPaddingTop();
            int paddingBottom = getPaddingBottom();

            setPadding(getPaddingLeft(), paddingTop + top, getPaddingRight(), paddingBottom - bottom);
        }
    }
}
