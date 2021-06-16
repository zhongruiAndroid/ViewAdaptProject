package com.test.viewadapt;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;

public class TestTextView extends androidx.appcompat.widget.AppCompatTextView {

    public TestTextView(Context context) {
        super(context);
    }

    public TestTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void setTextSize(int unit, float size) {
        super.setTextSize(unit, size);
    }

    @Override
    public void setTextSize(float size) {
        super.setTextSize(size);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setAutoSizeTextTypeUniformWithConfiguration(int autoSizeMinTextSize, int autoSizeMaxTextSize, int autoSizeStepGranularity, int unit) throws IllegalArgumentException {
        super.setAutoSizeTextTypeUniformWithConfiguration(autoSizeMinTextSize, autoSizeMaxTextSize, autoSizeStepGranularity, unit);
    }
}
