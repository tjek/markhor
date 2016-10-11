package com.shopgun.android.utils;

import android.content.res.Configuration;
import android.support.v4.view.ViewPager;

public class ToStringUtils {

    public static String pageScrollState(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING: return "SCROLL_STATE_DRAGGING";
            case ViewPager.SCROLL_STATE_IDLE: return "SCROLL_STATE_IDLE";
            case ViewPager.SCROLL_STATE_SETTLING: return "SCROLL_STATE_SETTLING";
            default: return "SCROLL_STATE_UNKNOWN";
        }
    }

    public static String orientation(int orientation) {
        switch (orientation) {
            case Configuration.ORIENTATION_UNDEFINED: return "ORIENTATION_UNDEFINED";
            case Configuration.ORIENTATION_PORTRAIT: return "ORIENTATION_PORTRAIT";
            case Configuration.ORIENTATION_LANDSCAPE: return "ORIENTATION_LANDSCAPE";
            case Configuration.ORIENTATION_SQUARE: return "ORIENTATION_SQUARE";
            default: return "ORIENTATION_UNKNOWN";
        }
    }

}
