package com.test.viewadapt;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class FullScreenUtils {
    private static boolean isNull(Object object) {
        return object == null;
    }

    /**************************************进入全屏**************************************/
    public static void fullScreenCompat(Fragment fragment, boolean intoFull, boolean intoCutout) {
        if (fragment == null) {
            return;
        }
        fullScreenCompat(fragment.getActivity(), intoFull,intoCutout);
    }

    public static void fullScreenCompat(Activity activity, boolean intoFull,boolean intoCutout) {
        if (activity == null) {
            return;
        }
        fullScreenCompat(activity.getWindow(), intoFull,intoCutout);
    }

    public static void fullScreenCompat(Window window, boolean intoFull,boolean intoCutout) {
        if (window == null) {
            return;
        }
        if(intoFull){
            if(intoCutout){
                intoCutout(window);
            }
            window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }else{
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        }
    }
    public static void intoCutout(Window window ){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            window.setAttributes(lp);
        }
    }
    /**************************************进入全屏**************************************/
    public static void fullScreen(Fragment fragment, boolean intoFull, boolean intoCutout) {
        if (fragment == null) {
            return;
        }
        fullScreen(fragment.getActivity(), intoFull,intoCutout);
    }

    public static void fullScreen(Activity activity, boolean intoFull,boolean intoCutout) {
        if (activity == null) {
            return;
        }
        fullScreen(activity.getWindow(), intoFull,intoCutout);
    }

    public static void fullScreen(Window window, boolean intoFull,boolean intoCutout) {
        if (window == null) {
            return;
        }
        if(intoCutout){
            intoCutout(window);
        }
        fullScreen(window.getDecorView(), intoFull);
    }

    public static void fullScreen(View view, boolean intoFull) {
        if (view == null) {
            return;
        }
        if (intoFull) { //全屏
            view.setSystemUiVisibility(
                    view.getSystemUiVisibility()
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            );
        } else { //非全屏
            int systemUiVisibility = view.getSystemUiVisibility();
            systemUiVisibility&=~View.SYSTEM_UI_FLAG_FULLSCREEN;
            systemUiVisibility&=~View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            systemUiVisibility&=~View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            systemUiVisibility&=~View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            systemUiVisibility&=~View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            systemUiVisibility&=~View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            view.setSystemUiVisibility(systemUiVisibility);
        }
    }

    /************************************************showStatusBar******************************************************/
    public static void showStatusBar(Activity activity, boolean statusBarIsSticky) {
        if (isNull(activity)) {
            return;
        }
        showStatusBar(activity.getWindow(), statusBarIsSticky);
    }

    public static void showStatusBar(Fragment fragment, boolean statusBarIsSticky) {
        if (isNull(fragment)) {
            return;
        }
        showStatusBar(fragment.getActivity(), statusBarIsSticky);
    }

    public static void showStatusBar(android.app.Fragment fragment, boolean statusBarIsSticky) {
        if (isNull(fragment)) {
            return;
        }
        showStatusBar(fragment.getActivity(), statusBarIsSticky);
    }

    public static void showStatusBar(Window window, boolean statusBarIsSticky) {
        if (isNull(window)) {
            return;
        }
        showStatusBar(window.getDecorView(), statusBarIsSticky);
    }

    public static void showStatusBar(View decorView, boolean statusBarIsSticky) {
        if (isNull(decorView)) {
            return;
        }
        int systemUiVisibility = decorView.getSystemUiVisibility();
        systemUiVisibility&=~View.SYSTEM_UI_FLAG_FULLSCREEN;
        if(!statusBarIsSticky){
            systemUiVisibility&=~View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            systemUiVisibility&=~View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        }
        decorView.setSystemUiVisibility(systemUiVisibility);
    }

    /**************************************隐藏状态栏，布局伸入状态栏**************************************/
    public static void hiddenStatusBar(Activity activity) {
        if (isNull(activity)) {
            return;
        }
        hiddenStatusBar(activity.getWindow());
    }

    public static void hiddenStatusBar(Fragment fragment) {
        if (isNull(fragment)) {
            return;
        }
        hiddenStatusBar(fragment.getActivity());
    }

    public static void hiddenStatusBar(android.app.Fragment fragment) {
        if (isNull(fragment)) {
            return;
        }
        hiddenStatusBar(fragment.getActivity());
    }

    public static void hiddenStatusBar(Window window) {
        if (isNull(window)) {
            return;
        }
        hiddenStatusBar(window.getDecorView());
    }

    public static void hiddenStatusBar(View view) {
        if (view == null) {
            return;
        }
        int systemUiVisibility = view.getSystemUiVisibility();
        view.setSystemUiVisibility(systemUiVisibility|
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        );
    }
    /*********************************************navigation*********************************************************/
    public static void showNavigationBar(Activity activity) {
        if (isNull(activity)) {
            return;
        }
        showNavigationBar(activity.getWindow());
    }

    public static void showNavigationBar(Fragment fragment) {
        if (isNull(fragment)) {
            return;
        }
        showNavigationBar(fragment.getActivity());
    }

    public static void showNavigationBar(android.app.Fragment fragment) {
        if (isNull(fragment)) {
            return;
        }
        showNavigationBar(fragment.getActivity());
    }

    public static void showNavigationBar(Window window) {
        if (isNull(window)) {
            return;
        }
        showNavigationBar(window.getDecorView());
    }

    public static void showNavigationBar(View decorView) {
        if (isNull(decorView)) {
            return;
        }
        int systemUiVisibility = decorView.getSystemUiVisibility();
        systemUiVisibility&=~View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(systemUiVisibility);
    }

    public static void hideNavigationBar(Activity activity) {
        if (isNull(activity)) {
            return;
        }
        hideNavigationBar(activity.getWindow());
    }

    public static void hideNavigationBar(Fragment fragment) {
        if (isNull(fragment)) {
            return;
        }
        hideNavigationBar(fragment.getActivity());
    }

    public static void hideNavigationBar(android.app.Fragment fragment) {
        if (isNull(fragment)) {
            return;
        }
        hideNavigationBar(fragment.getActivity());
    }

    public static void hideNavigationBar(Window window) {
        if (isNull(window)) {
            return;
        }
        hideNavigationBar(window.getDecorView());
    }

    public static void hideNavigationBar(View decorView) {
        if (decorView == null) {
            return;
        }
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility()
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
        );
    }

    public static void setStatusBarFontColor(Fragment fragment, boolean isBlackFont) {
        if (fragment == null) {
            return;
        }
        setStatusBarFontColor(fragment.getActivity(), isBlackFont);
    }
    public static void setStatusBarFontColor(Activity activity, boolean isBlackFont) {
        if (activity == null) {
            return;
        }
        setStatusBarFontColor(activity.getWindow(), isBlackFont);
    }
    public static void setStatusBarFontColor(Window window, boolean isBlackFont) {
        if (window == null) {
            return;
        }
        setStatusBarFontColor(window.getDecorView(), isBlackFont);
    }
    public static void setStatusBarFontColor(View view, boolean isBlackFont) {
        if (view == null) {
            return;
        }
        int systemUiVisibility = view.getSystemUiVisibility();

        if(isBlackFont){
            systemUiVisibility=systemUiVisibility|View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(systemUiVisibility);
            return;
        }

        systemUiVisibility&=~View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
        systemUiVisibility&=~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        view.setSystemUiVisibility(systemUiVisibility);
    }
    public static int getStatusBarHeight(Context context) {
        if(context==null){
            throw new IllegalStateException("context must not null");
        }
        int height = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = context.getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }

    /**
     * 虚拟按键隐藏之后无法获取高度
     *
     * @param activity
     * @return
     */
    public static int getNavigationBarHeight(Activity activity) {
        if(activity==null){
            return 0;
        }
        DisplayMetrics metrics = new DisplayMetrics();
        //这个方法获取可能不是真实屏幕的高度(可能不包含底部导航栏高度)
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int usableHeight = metrics.heightPixels;
        //获取当前屏幕的真实高度
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        }
        int realHeight = metrics.heightPixels;
        if (realHeight > usableHeight) {
            return realHeight - usableHeight;
        } else {
            return 0;
        }
    }
    /**************************************复原**************************************/
    public static void reset(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        reset(fragment.getActivity());
    }

    public static void reset(Activity activity) {
        if (activity == null) {
            return;
        }
        reset(activity.getWindow());
    }

    public static void reset(Window window) {
        if (window == null) {
            return;
        }
        reset(window.getDecorView());
    }

    public static void reset(View view) {
        if (view == null) {
            return;
        }
        view.setSystemUiVisibility(0);
    }
}
