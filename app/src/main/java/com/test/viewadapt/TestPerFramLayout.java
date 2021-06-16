package com.test.viewadapt;

import android.content.Context;
import androidx.percentlayout.widget.PercentFrameLayout;
import android.util.AttributeSet;

public class TestPerFramLayout extends PercentFrameLayout {
    public TestPerFramLayout(Context context) {
        super(context);
    }

    public TestPerFramLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestPerFramLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
