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

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import java.util.concurrent.atomic.AtomicInteger;

public class ViewUtils {

    public static final String TAG = Tag.from(ViewUtils.class);

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

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

}
