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
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

public class BitmapUtils {

    public static final String TAG = Tag.from(BitmapUtils.class);

    /**
     * Convert any view into a Bitmap
     *
     * @param view A View to convert
     * @return A bitmap
     */
    public static Bitmap getBitmapFromView(View view, int defBackgroundColor) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(defBackgroundColor);
        }
        view.draw(canvas);
        return returnedBitmap;
    }

    /**
     * returns the bytesize of the give bitmap
     * @param bitmap A bitmap to measure
     * @return Size of bitmap, in byte
     */
    public static int sizeOf(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return bitmap.getAllocationByteCount();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            return bitmap.getByteCount();
        } else {
            return bitmap.getRowBytes() * bitmap.getHeight();
        }
    }

    /**
     * returns the kilobytesize of the give bitmap
     * @param bitmap A bitmap to measure
     * @return Size of bitmap, in kilo byte
     */
    public static int sizeOfKb(Bitmap bitmap) {
        return sizeOf(bitmap) / 1024;
    }

}
