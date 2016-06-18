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

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import java.util.Locale;

public class UnitUtils {

    public static final String TAG = Tag.from(UnitUtils.class);

    private UnitUtils() {
        // private constructor
    }

    public static float dpToPx(float dp, Context ctx){
        DisplayMetrics metrics = ctx.getResources().getDisplayMetrics();
        return dp * metrics.density;
    }

    public static int dpToPx(int dp, Context ctx){
        DisplayMetrics metrics = ctx.getResources().getDisplayMetrics();
        return (int) (dp * metrics.density);
    }

    public static float pxToDp(float px, Context ctx) {
        DisplayMetrics metrics = ctx.getResources().getDisplayMetrics();
        return px / metrics.density;
    }

    public static int pxToDp(int px, Context ctx) {
        DisplayMetrics metrics = ctx.getResources().getDisplayMetrics();
        return (int) (px / metrics.density);
    }

    public static float spToPx(float sp, Context ctx) {
        DisplayMetrics metrics = ctx.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, metrics);
    }

    public static int spToPx(int sp, Context ctx) {
        DisplayMetrics metrics = ctx.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, metrics);
    }

    public static float pxToSp(float px, Context ctx) {
        DisplayMetrics metrics = ctx.getResources().getDisplayMetrics();
        return px / metrics.scaledDensity;
    }

    public static int pxToSp(int px, Context ctx) {
        DisplayMetrics metrics = ctx.getResources().getDisplayMetrics();
        return px / (int) metrics.scaledDensity;
    }

    /**
     * <p>Method for converting a size (in bytes) into a human readable format.</p>
     *
     * <table style="text-align: right; border: #000000 solid 1px " summary="">
     * <tr><th>input</th>	<th>SI</th>			<th>BINARY</th></tr>
     * <tr><td>0</td>		<td>0 B</td>		<td>0 B</td></tr>
     * <tr><td>27</td>		<td>27 B</td>		<td>27 B</td></tr>
     * <tr><td>1023</td>	<td>1.0 kB</td>		<td>1023 B</td></tr>
     * <tr><td>1024</td>	<td>1.0 kB</td>		<td>1.0 KiB</td></tr>
     * </table>
     *
     * <p>Same system as above for larger values.</p>
     *
     * @param bytes A number of bytes to convert
     * @param si    Use SI units, or binary form
     * @return A human readable string of the byte-size
     */
    public static String humanReadableByteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format(Locale.US, "%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

}
