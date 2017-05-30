/*******************************************************************************
 * Copyright 2015 ShopGun
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.shopgun.android.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.inputmethod.EditorInfo;

import java.util.concurrent.atomic.AtomicInteger;

public class ViewUtils {

    public static final String TAG = Tag.from(ViewUtils.class);

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    private static final int DEF_FADE_DURATION = 150;

    private ViewUtils() {
        // private constructor
    }

    public static boolean isClick(int actionId, KeyEvent event) {
        return actionId == EditorInfo.IME_ACTION_DONE ||
                actionId == EditorInfo.IME_ACTION_SEARCH ||
                actionId == EditorInfo.IME_NULL ||
                (event != null && (
                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER));
    }

    /**
     * Generate a value suitable for use in {@link View#setId(int)}.
     * This value will not collide with ID values generated at build time by aapt for R.id.
     *
     * @return a generated ID value
     */
    public static int generateViewId() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return generateViewIdPreJellyBeans();
        } else {
            return View.generateViewId();
        }
    }

    /**
     * Generate a value suitable for use in {@link View#setId(int)}.
     * This value will not collide with ID values generated at build time by aapt for R.id.
     *
     * @return a generated ID value
     */
    private static int generateViewIdPreJellyBeans() {
        for (;;) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    /**
     * Convert any view into a Bitmap
     *
     * @param view A View to convert
     * @param defBackgroundColor A default color to apply to the background, in a background doesn't exist
     * @return A bitmap
     */
    public static Bitmap viewToBitmap(View view, int defBackgroundColor) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable drawable = view.getBackground();
        if (drawable != null) {
            drawable.draw(canvas);
        } else {
            canvas.drawColor(defBackgroundColor);
        }
        view.draw(canvas);
        return bitmap;
    }

    /**
     * Convert a drawable into a Bitmap
     *
     * @param drawable A drawable to convert
     * @return A bitmap
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        Bitmap bitmap;
        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            // Single color bitmap will be created of 1x1 pixel - drawable is just a solid color
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * @param view A view
     * @return {@code true} if the view is {@link View#VISIBLE}, else {@code false}
     */
    public static boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }

    /**
     * @param view A view
     * @return {@code true} if the view is {@link View#GONE}, else {@code false}
     */
    public static boolean isGone(View view) {
        return view.getVisibility() == View.GONE;
    }

    /**
     * @param view A view
     * @return {@code true} if the view is {@link View#INVISIBLE}, else {@code false}
     */
    public static boolean isInvisible(View view) {
        return view.getVisibility() == View.INVISIBLE;
    }

    /**
     * Cross fade between two views
     * @param in The view to fade in
     * @param out The view to fade out
     * @return An {@link AnimationSet} of the animations that are being performed
     */
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
    public static AnimationSet crossFadeViews(final View in, final View out) {
        return crossFadeViews(in, out, DEF_FADE_DURATION);
    }

    /**
     * Cross fade between two views
     * @param in The view to fade in
     * @param out The view to fade out
     * @param duration The duration of the animation
     * @return An {@link AnimationSet} of the animations that are being performed
     */
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
    public static AnimationSet crossFadeViews(final View in, final View out, int duration) {
        ViewPropertyAnimator inAnimator = fadeIn(in, duration);
        ViewPropertyAnimator outAnimator = fadeOut(out, duration);
        return new AnimationSet(inAnimator, outAnimator);
    }

    /**
     * Fade a view in from {@code alpha = 0} to {@code alpha = 1}, hence the visibility if the
     * {@link View} will be set to {@link View#VISIBLE}.
     * @param in The view to fade in
     * @return The {@link ViewPropertyAnimator} for the given animation
     */
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
    public static ViewPropertyAnimator fadeIn(final View in) {
        return fadeIn(in, DEF_FADE_DURATION);
    }

    /**
     * Fade a view in from {@code alpha = 0} to {@code alpha = 1}, hence the visibility if the
     * {@link View} will be set to {@link View#VISIBLE}.
     * @param in The view to fade in
     * @param duration The duration of the animation
     * @return The {@link ViewPropertyAnimator} for the given animation
     */
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
    public static ViewPropertyAnimator fadeIn(final View in, int duration) {

        // Set the content view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
        in.setAlpha(0f);
        in.setVisibility(View.VISIBLE);

        // Animate the content view to 100% opacity, and clear any animation
        // listener set on the view.
        ViewPropertyAnimator animator = in.animate();
        animator.alpha(1f)
                .setDuration(duration)
                .setListener(null);
        return animator;
    }

    /**
     * Fade a view out from {@code alpha = 1} to {@code alpha = 0}. Once the animation finished
     * the visibility of the view is set to {@link View#GONE} and alpha {@code alpha = 1}.
     * @param out The view to fade out
     * @return The {@link ViewPropertyAnimator} for the given animation
     */
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
    public static ViewPropertyAnimator fadeOut(final View out) {
        return fadeOut(out, DEF_FADE_DURATION);
    }

    /**
     * Fade a view out from {@code alpha = 1} to {@code alpha = 0}. Once the animation finished
     * the visibility of the view is set to {@link View#GONE} and alpha {@code alpha = 1}.
     * @param out The view to fade out
     * @param duration The duration of the animation
     * @return The {@link ViewPropertyAnimator} for the given animation
     */
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
    public static ViewPropertyAnimator fadeOut(final View out, int duration) {

        // Animate the loading view to 0% opacity. After the animation ends,
        // set its visibility to GONE as an optimization step (it won't
        // participate in layout passes, etc.)
        ViewPropertyAnimator animator = out.animate();
        animator.alpha(0f)
                .setDuration(duration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        out.setVisibility(View.GONE);
                        out.setAlpha(1f);
                    }
                });
        return animator;
    }

    public static class AnimationSet {

        private ViewPropertyAnimator mInAnimator;
        private ViewPropertyAnimator mOutAnimator;

        public AnimationSet() {
        }

        public AnimationSet(ViewPropertyAnimator inAnimator, ViewPropertyAnimator outAnimator) {
            this.mInAnimator = inAnimator;
            this.mOutAnimator = outAnimator;
        }

        @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
        public void cancel() {
            cancel(mInAnimator);
            cancel(mOutAnimator);
        }

        public ViewPropertyAnimator getInAnimator() {
            return mInAnimator;
        }

        public ViewPropertyAnimator getOutAnimator() {
            return mOutAnimator;
        }

        @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
        private static void cancel(ViewPropertyAnimator animator) {
            if (animator != null) animator.cancel();
        }

    }

}
