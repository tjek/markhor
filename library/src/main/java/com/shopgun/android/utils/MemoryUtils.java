package com.shopgun.android.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;

public class MemoryUtils {

    public static final String TAG = MemoryUtils.class.getSimpleName();

    private MemoryUtils() {
        //no instance
    }
    
    /**
     * Get the max available heap size
     *
     * @param c A context
     * @return the maximum available heap size for the device
     */
    public static int getMaxHeap(Context c) {
        ActivityManager am = (ActivityManager) c.getSystemService(Context.ACTIVITY_SERVICE);
        return am.getLargeMemoryClass();
    }

}
