package com.shopgun.android.utils;

import android.view.animation.Animation;

public class AnimationUtils {

    public static float getProgress(Animation a) {
        long now = android.view.animation.AnimationUtils.currentAnimationTimeMillis();
        float progress = (float)(now - (a.getStartTime() - a.getStartOffset()));
        return progress / (float)a.getDuration();
    }

    public static float getInterpolation(Animation a) {
        return getInterpolation(a, getProgress(a));
    }

    public static float getInterpolation(Animation a, float progress) {
        return a.getInterpolator().getInterpolation(progress);
    }

    public static boolean isRunning(Animation a) {
        return a != null && a.hasStarted() && !a.hasEnded();
    }

}
