package com.zhongrui;

import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class LayoutAdaptHelper {
    private final ViewGroup mHost;
    public LayoutAdaptHelper(@NonNull ViewGroup host) {
        if (host == null) {
            throw new IllegalArgumentException("host must be non-null");
        }
        mHost = host;
    }
    public static void fetchWidthAndHeight(ViewGroup.LayoutParams params, TypedArray array,int widthAttr, int heightAttr) {
        params.width = array.getLayoutDimension(widthAttr, 0);
        params.height = array.getLayoutDimension(heightAttr, 0);
    }
    public void adjustChildren(int widthMeasureSpec, int heightMeasureSpec) {

        // Calculate available space, accounting for host's paddings
        int widthHint = View.MeasureSpec.getSize(widthMeasureSpec) - mHost.getPaddingLeft()
                - mHost.getPaddingRight();
        int heightHint = View.MeasureSpec.getSize(heightMeasureSpec) - mHost.getPaddingTop()
                - mHost.getPaddingBottom();
        for (int i = 0, N = mHost.getChildCount(); i < N; i++) {
            View view = mHost.getChildAt(i);
            ViewGroup.LayoutParams params = view.getLayoutParams();

            if (params instanceof PercentLayoutParams) {
                PercentLayoutInfo info =
                        ((PercentLayoutParams) params).getPercentLayoutInfo();
                if (DEBUG) {
                    Log.d(TAG, "using " + info);
                }
                if (info != null) {
                    if (params instanceof ViewGroup.MarginLayoutParams) {
                        info.fillMarginLayoutParams(view, (ViewGroup.MarginLayoutParams) params,
                                widthHint, heightHint);
                    } else {
                        info.fillLayoutParams(params, widthHint, heightHint);
                    }
                }
            }
        }
    }

}
