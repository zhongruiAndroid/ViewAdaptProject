package com.zhongrui;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import static android.os.Build.VERSION_CODES.JELLY_BEAN_MR1;

public class RelativeLayoutAdapt extends RelativeLayout implements LayoutAdaptHelper.AdaptView {
    private LayoutAdaptHelper mHelper = new LayoutAdaptHelper(this);

    /*需要判断状态栏是否隐藏*/
    public RelativeLayoutAdapt(@NonNull Context context) {
        super(context);
        init(null, R.attr.LayoutAdaptAttr, R.style.LayoutAdaptStyle);
    }

    public RelativeLayoutAdapt(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs, R.attr.LayoutAdaptAttr, R.style.LayoutAdaptStyle);
    }

    public RelativeLayoutAdapt(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr, R.style.LayoutAdaptStyle);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RelativeLayoutAdapt(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs, defStyleAttr, defStyleRes);
    }

    private void init(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        mHelper.obtainStyledAttributes(this,getContext(), attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mHelper.canUseAdapt()) {
            mHelper.adjustChildren();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mHelper.canUseAdapt()) {
            adaptSelf();
        }
    }

    private void adaptSelf() {
        if (getParent() == null) {
            return;
        }
        /*如果XXXLayoutAdapt的父view是系统ViewGroup,并且自身有设置layout_adapt_width或者layout_adapt_height，则自己适配自己的宽高*/
        if (!(getParent() instanceof LayoutAdaptHelper.AdaptView)) {
            int selfWidth = mHelper.selfWidth;
            int selfHeight = mHelper.selfHeight;
            if (selfWidth > 0 && selfHeight > 0) {
                setMeasuredDimension(mHelper.getRealSizeInt(this, selfWidth), mHelper.getRealSizeInt(this, selfHeight));
            } else if (selfWidth > 0) {
                setMeasuredDimension(mHelper.getRealSizeInt(this, selfWidth), getMeasuredHeight());
            } else if (selfHeight > 0) {
                setMeasuredDimension(getMeasuredWidth(), mHelper.getRealSizeInt(this, selfHeight));
            }
        }
    }

    public void setPaddingAdapt(int left, int top, int right, int bottom) {
        super.setPadding(mHelper.getRealSizeInt(this, left), mHelper.getRealSizeInt(this, top), mHelper.getRealSizeInt(this, right), mHelper.getRealSizeInt(this, bottom));
    }

    @RequiresApi(api = JELLY_BEAN_MR1)
    public void setPaddingRelativeAdapt(int start, int top, int end, int bottom) {
        super.setPaddingRelative(mHelper.getRealSizeInt(this, start), mHelper.getRealSizeInt(this, top), mHelper.getRealSizeInt(this, end), mHelper.getRealSizeInt(this, bottom));
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
        if (lp instanceof LayoutParams) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                return new LayoutParams((LayoutParams) lp);
            }
        } else if (lp instanceof MarginLayoutParams) {
            return new LayoutParams((MarginLayoutParams) lp);
        }
        return new LayoutParams(lp);
    }

    public static class LayoutParams extends RelativeLayout.LayoutParams implements LayoutAdaptHelper.LayoutAdaptParams {
        private LayoutParamsInfo info;

        public LayoutParams(@NonNull Context c, @Nullable AttributeSet attrs) {
            super(c, attrs);
            getLayoutAdaptInfo().getAttributeSet(c, attrs);
        }

        public LayoutParams(int w, int h) {
            super(w, h);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public LayoutParams(RelativeLayout.LayoutParams source) {
            super(source);
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public LayoutParams(LayoutParams source) {
            super(source);
            info = source.info;
        }

        @Override
        public LayoutParamsInfo getLayoutAdaptInfo() {
            if (info == null) {
                info = new LayoutParamsInfo();
            }
            return info;
        }
    }
}
