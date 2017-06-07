package com.shopgun.android.utils;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;

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

    /**
     * Get the {@link android.view.View.MeasureSpec} mode, as a {@code String}
     * @param mode A {@link android.view.View.MeasureSpec} mode
     * @return The MeasureSpec mode as a string
     */
    public static String measureSpecMode(int mode) {
        switch (mode) {
            case MeasureSpec.UNSPECIFIED: return "UNSPECIFIED";
            case MeasureSpec.EXACTLY: return "EXACTLY";
            case MeasureSpec.AT_MOST: return "AT_MOST";
            default: return "UNSPECIFIED";
        }
    }

    public static String visibility(View view) {
        return visibility(view.getVisibility());
    }

    public static String visibility(int visibility) {
        switch (visibility) {
            case View.VISIBLE: return "VISIBLE";
            case View.INVISIBLE: return "INVISIBLE";
            case View.GONE: return "GONE";
            default: return "UNKNOWN";
        }
    }

    /**
     * Returns the measurespec in a readable format:
     * <p>E.g.: MeasureSpec[ w: AT_MOST 1845, h: EXACTLY 987 ]</p>
     *
     * @param widthMeasureSpec the measure specification for width based on size and mode
     * @param heightMeasureSpec the measure specification for height based on size and mode
     * @return a human readable string representing the MeasureSpec
     */
    public static String measureSpec(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        // MeasureSpec[ w: AT_MOST 1845, h: EXACTLY 987 ]
        return String.format("MeasureSpec[ w: %s %s, h: %s %s ]",
                measureSpecMode(widthMode).toLowerCase(), widthSize,
                measureSpecMode(heightMode).toLowerCase(), heightSize);
    }

    public static String motionEventAction(MotionEvent ev) {
        return motionEventAction(ev.getAction() & MotionEvent.ACTION_MASK);
    }

    public static String motionEventAction(int action) {
        switch (action) {
            case MotionEvent.ACTION_DOWN: return "ACTION_DOWN";
            case MotionEvent.ACTION_UP: return "ACTION_UP";
            case MotionEvent.ACTION_MOVE: return "ACTION_MOVE";
            case MotionEvent.ACTION_CANCEL: return "ACTION_CANCEL";
            case MotionEvent.ACTION_OUTSIDE: return "ACTION_OUTSIDE";
            case MotionEvent.ACTION_POINTER_DOWN: return "ACTION_POINTER_DOWN";
            case MotionEvent.ACTION_POINTER_UP: return "ACTION_POINTER_UP";
            case MotionEvent.ACTION_HOVER_MOVE: return "ACTION_HOVER_MOVE";
            case MotionEvent.ACTION_SCROLL: return "ACTION_SCROLL";
            case MotionEvent.ACTION_HOVER_ENTER: return "ACTION_HOVER_ENTER";
            case MotionEvent.ACTION_HOVER_EXIT: return "ACTION_HOVER_EXIT";
            case MotionEvent.ACTION_BUTTON_PRESS: return "ACTION_BUTTON_PRESS";
            case MotionEvent.ACTION_BUTTON_RELEASE: return "ACTION_BUTTON_RELEASE";
            case MotionEvent.ACTION_POINTER_INDEX_MASK: return "ACTION_POINTER_INDEX_MASK";
            case MotionEvent.ACTION_POINTER_2_DOWN: return "ACTION_POINTER_2_DOWN";
            case MotionEvent.ACTION_POINTER_3_DOWN: return "ACTION_POINTER_3_DOWN";
            case MotionEvent.ACTION_POINTER_2_UP: return "ACTION_POINTER_2_UP";
            case MotionEvent.ACTION_POINTER_3_UP: return "ACTION_POINTER_3_UP";
            default: return "ACTION_UNKNOWN";
        }
    }

    public static String onTrimMemoryToString(int level) {
        switch (level) {
            case ComponentCallbacks2.TRIM_MEMORY_BACKGROUND: return "TRIM_MEMORY_BACKGROUND";
            case ComponentCallbacks2.TRIM_MEMORY_COMPLETE: return "TRIM_MEMORY_COMPLETE";
            case ComponentCallbacks2.TRIM_MEMORY_MODERATE: return "TRIM_MEMORY_MODERATE";
            case ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL: return "TRIM_MEMORY_RUNNING_CRITICAL";
            case ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW: return "TRIM_MEMORY_RUNNING_LOW";
            case ComponentCallbacks2.TRIM_MEMORY_RUNNING_MODERATE: return "TRIM_MEMORY_RUNNING_MODERATE";
            case ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN: return "TRIM_MEMORY_UI_HIDDEN";
            default: return "UNKNOWN";
        }
    }

    public static String logLevelToString(int logLevel) {
        switch (logLevel) {
            case Log.VERBOSE: return "VERBOSE";
            case Log.DEBUG: return "DEBUG";
            case Log.INFO: return "INFO";
            case Log.WARN: return "WARN";
            case Log.ERROR: return "ERROR";
            case Log.ASSERT: return "ASSERT";
            default: return "UNKNOWN";
        }
    }

}
