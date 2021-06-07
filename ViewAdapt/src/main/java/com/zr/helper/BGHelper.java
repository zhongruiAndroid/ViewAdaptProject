package com.zr.helper;

import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import com.zr.LayoutAdaptHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class BGHelper {
    public static Drawable drawableAdapt(View view, Drawable d, LayoutAdaptHelper helper) {
        if (view == null || d == null || helper == null) {
            return d;
        }
        Drawable drawable = d.mutate();
        try {
            if (drawable instanceof LayerDrawable) {
                return getLayerDrawable(view, (LayerDrawable) drawable, helper);
            }
            if (drawable instanceof GradientDrawable) {
                return getGradientDrawable(view, (GradientDrawable) drawable, helper);
            }
            return drawable;
        } catch (Exception e) {
            e.printStackTrace();
            return d;
        }
    }

    private static Drawable getLayerDrawable(View view, LayerDrawable layerDrawable, LayoutAdaptHelper helper) throws Exception {
        if (view == null || layerDrawable == null || helper == null) {
            return layerDrawable;
        }
        /*layer-list标签 padding*/
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            boolean needSetPadding = false;
            boolean needSetPaddingRelative = false;
            int leftPadding = layerDrawable.getLeftPadding();
            if (leftPadding != -1) {
                needSetPadding = true;
                leftPadding = helper.getRealSizeInt(view, leftPadding);
            }
            int topPadding = layerDrawable.getTopPadding();
            if (topPadding != -1) {
                needSetPadding = true;
                needSetPaddingRelative = true;
                topPadding = helper.getRealSizeInt(view, topPadding);
            }

            int rightPadding = layerDrawable.getRightPadding();
            if (rightPadding != -1) {
                needSetPadding = true;
                rightPadding = helper.getRealSizeInt(view, rightPadding);
            }

            int bottomPadding = layerDrawable.getBottomPadding();
            if (bottomPadding != -1) {
                needSetPadding = true;
                needSetPaddingRelative = true;
                bottomPadding = helper.getRealSizeInt(view, bottomPadding);
            }


            int startPadding = layerDrawable.getStartPadding();
            if (startPadding != -1) {
                needSetPaddingRelative = true;
                startPadding = helper.getRealSizeInt(view, startPadding);
            }

            int endPadding = layerDrawable.getEndPadding();
            if (endPadding != -1) {
                needSetPaddingRelative = true;
                endPadding = helper.getRealSizeInt(view, endPadding);
            }

            if (needSetPadding) {
                layerDrawable.setPadding(leftPadding,
                        topPadding,
                        rightPadding,
                        bottomPadding);
            }
            if (needSetPaddingRelative) {
                layerDrawable.setPaddingRelative(startPadding,
                        topPadding,
                        endPadding,
                        bottomPadding);

            }


        }
        int numberOfLayers = layerDrawable.getNumberOfLayers();
        for (int i = 0; i < numberOfLayers; i++) {

            /*item标签 属性*/
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

                int layerWidth = layerDrawable.getLayerWidth(i);
                if (layerWidth > 0) {
                    layerWidth = helper.getRealSizeInt(view, layerWidth);
                    layerDrawable.setLayerWidth(i, layerWidth);
                }
                int layerHeight = layerDrawable.getLayerHeight(i);
                if (layerHeight > 0) {
                    layerHeight = helper.getRealSizeInt(view, layerHeight);
                    layerDrawable.setLayerHeight(i, layerHeight);
                }


                int layerInsetLeft = layerDrawable.getLayerInsetLeft(i);
                if (layerInsetLeft != 0) {
                    layerInsetLeft = helper.getRealSizeInt(view, layerInsetLeft);
                    layerDrawable.setLayerInsetLeft(i, layerInsetLeft);

                }
                int layerInsetTop = layerDrawable.getLayerInsetTop(i);
                if (layerInsetTop != 0) {
                    layerInsetTop = helper.getRealSizeInt(view, layerInsetTop);
                    layerDrawable.setLayerInsetTop(i, layerInsetTop);

                }
                int layerInsetRight = layerDrawable.getLayerInsetRight(i);
                if (layerInsetRight != 0) {
                    layerInsetRight = helper.getRealSizeInt(view, layerInsetRight);
                    layerDrawable.setLayerInsetRight(i, layerInsetRight);

                }
                int layerInsetBottom = layerDrawable.getLayerInsetBottom(i);
                if (layerInsetBottom != 0) {
                    layerInsetBottom = helper.getRealSizeInt(view, layerInsetBottom);
                    layerDrawable.setLayerInsetBottom(i, layerInsetBottom);

                }


                int layerInsetStart = layerDrawable.getLayerInsetStart(i);
                if (layerInsetStart != LayerDrawable.INSET_UNDEFINED && layerInsetStart != 0) {
                    layerInsetStart = helper.getRealSizeInt(view, layerInsetStart);
                    layerDrawable.setLayerInsetStart(i, layerInsetStart);
                }
                int layerInsetEnd = layerDrawable.getLayerInsetEnd(i);
                if (layerInsetEnd != LayerDrawable.INSET_UNDEFINED && layerInsetEnd != 0) {
                    layerInsetEnd = helper.getRealSizeInt(view, layerInsetEnd);
                    layerDrawable.setLayerInsetEnd(i, layerInsetEnd);
                }
            }
            Drawable drawable = layerDrawable.getDrawable(i);
            if (drawable == null) {
                continue;
            }

            if (drawable instanceof LayerDrawable) {
                getLayerDrawable(view, (LayerDrawable) drawable, helper);
                continue;
            }

            if (drawable instanceof GradientDrawable) {
                getGradientDrawable(view, (GradientDrawable) drawable, helper);
                continue;
            }
        }
        return layerDrawable;
    }

    private static Drawable getGradientDrawable(View view, GradientDrawable gradientDrawable, LayoutAdaptHelper helper) throws Exception {
        if (view == null || gradientDrawable == null || helper == null) {
            return gradientDrawable;
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            /*corners*/
            /*如果shape没有设置具体某个圆角，这个地方会报空指针*/
            float[] cornerRadii = null;
            try {
                cornerRadii = gradientDrawable.getCornerRadii();
            } catch (Exception e) {
            }
            float cornerRadius = gradientDrawable.getCornerRadius();
            float cornerRadiusRealSize = helper.getRealSize(view, cornerRadius);
            if (cornerRadii == null || cornerRadii.length <= 0) {
                gradientDrawable.setCornerRadius(cornerRadiusRealSize);
            } else {
                int length = cornerRadii.length;
                float[] cornerRadiiNew = new float[length];
                for (int i = 0; i < length; i++) {
                    float realSize = helper.getRealSize(view, cornerRadii[i]);
                    if (realSize == 0) {
                        realSize = cornerRadiusRealSize;
                    }
                    cornerRadiiNew[i] = realSize;
                }
                gradientDrawable.setCornerRadii(cornerRadiiNew);
            }
        }
        /*size*/
        int intrinsicWidth = gradientDrawable.getIntrinsicWidth();
        int intrinsicHeight = gradientDrawable.getIntrinsicHeight();

        int realSizeWidth = intrinsicWidth == -1 ? intrinsicWidth : helper.getRealSizeInt(view, intrinsicWidth);
        int realSizeHeight = intrinsicHeight == -1 ? intrinsicHeight : helper.getRealSizeInt(view, intrinsicHeight);

        gradientDrawable.setSize(realSizeWidth, realSizeHeight);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            /*gradient*/
            float gradientRadius = gradientDrawable.getGradientRadius();
            float realSize = helper.getRealSize(view, gradientRadius);
            gradientDrawable.setGradientRadius(realSize);
        }

        /*padding，stroke通过反射获取*/

        /*padding*/
        Rect rect = new Rect();
        gradientDrawable.getPadding(rect);
        boolean useAdapt = false;
        if (rect.left != 0) {
            useAdapt = true;
            float realSize = helper.getRealSizeInt(view, rect.left);
            rect.left = (int) realSize;
        }
        if (rect.top != 0) {
            useAdapt = true;
            float realSize = helper.getRealSizeInt(view, rect.top);
            rect.top = (int) realSize;
        }
        if (rect.right != 0) {
            useAdapt = true;
            float realSize = helper.getRealSizeInt(view, rect.right);
            rect.right = (int) realSize;
        }
        if (rect.bottom != 0) {
            useAdapt = true;
            float realSize = helper.getRealSizeInt(view, rect.bottom);
            rect.bottom = (int) realSize;
        }


        /*stroke*/
        Drawable.ConstantState constantState = gradientDrawable.getConstantState();
        if (constantState != null) {
            Class<? extends Drawable.ConstantState> stateClass = constantState.getClass();

            if (useAdapt) {
                Field mPadding = stateClass.getDeclaredField("mPadding");
                mPadding.setAccessible(true);
                mPadding.set(constantState, rect);
            }
                /*
                 public int mStrokeWidth = -1; // if >= 0 use stroking.
                 public float mStrokeDashWidth = 0.0f;
                 public float mStrokeDashGap = 0.0f;
                * */

            Field mStrokeWidth = stateClass.getField("mStrokeWidth");
            mStrokeWidth.setAccessible(true);
            int strokeWidth = (int) mStrokeWidth.get(constantState);
            if (strokeWidth > 0) {
                strokeWidth = helper.getRealSizeInt(view, strokeWidth);

            }

            Field mStrokeDashWidth = stateClass.getField("mStrokeDashWidth");
            mStrokeDashWidth.setAccessible(true);
            float strokeDashWidth = (float) mStrokeDashWidth.get(constantState);
            if (strokeDashWidth != 0) {
                strokeDashWidth = helper.getRealSize(view, strokeDashWidth);
            }

            float strokeDashGap = 0;
            if (strokeDashWidth != 0) {
                Field mStrokeDashGap = stateClass.getField("mStrokeDashGap");
                mStrokeDashGap.setAccessible(true);
                strokeDashGap = (float) mStrokeDashGap.get(constantState);
                if (strokeDashGap != 0) {
                    strokeDashGap = helper.getRealSize(view, strokeDashGap);
                }
            }


            if (strokeWidth >= 0) {
                Field mStrokePaint = gradientDrawable.getClass().getDeclaredField("mStrokePaint");
                mStrokePaint.setAccessible(true);
                Paint paint = (Paint) mStrokePaint.get(gradientDrawable);
                Class paintClass = paint.getClass();

                Method setStrokeWidth = paintClass.getMethod("setStrokeWidth", float.class);
                setStrokeWidth.setAccessible(true);
                setStrokeWidth.invoke(paint, strokeWidth);


                if (strokeDashWidth != 0) {
                    DashPathEffect e = new DashPathEffect(new float[]{strokeDashWidth, strokeDashGap}, 0);

                    Method setPathEffect = paintClass.getMethod("setPathEffect", PathEffect.class);
                    setPathEffect.setAccessible(true);
                    setPathEffect.invoke(paint, e);
                }

            }
        }
        return gradientDrawable;
    }

    public static boolean drawableAdaptEnable(LayoutAdaptHelper mHelper) {
        if (mHelper == null) {
            return false;
        }
        return mHelper.drawableAdaptEnable;
    }

    public static void resetDrawable(View view, Drawable preDrawable) {
        if (view == null || preDrawable == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(preDrawable);
        } else {
            view.setBackgroundDrawable(preDrawable);
        }
    }
    public static void resetImageDrawable(ImageView view, Drawable preDrawable) {
        if (view == null || preDrawable == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setImageDrawable(preDrawable);
        } else {
            view.setImageDrawable(preDrawable);
        }
    }
}
