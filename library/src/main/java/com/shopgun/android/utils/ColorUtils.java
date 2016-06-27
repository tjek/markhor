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

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;

import java.util.Locale;

public class ColorUtils {

    public static final String TAG = Tag.from(ColorUtils.class);

    private static final String ARGB_FORMAT = "#%08X";
    private static final String RGB_FORMAT = "#%06X";
    private static final String HSV_FORMAT = "hsv[%.2f, %.2f, %.2f]";

    /**
     * Returns the complimentary color. (Alpha channel remains intact)
     *
     * @param color An ARGB color to return the compliment of
     * @return An ARGB of compliment color
     */
    @ColorInt
    public static int getCompliment(@ColorInt int color) {
        // get existing colors
        int alpha = Color.alpha(color);
        int red = Color.red(color);
        int blue = Color.blue(color);
        int green = Color.green(color);

        // find compliments
        red = (~red) & 0xff;
        blue = (~blue) & 0xff;
        green = (~green) & 0xff;

        return Color.argb(alpha, red, green, blue);
    }

    /**
     * Convert a color to an ARGB string.
     * <p>i.e. {@link Color#BLUE Color.BLUE} returns the string {@code #FF0000FF}.</p>
     * @param color a color
     * @return the color as string, or null if {@code color} is {@code null}.
     */
    public static String toARGBString(@ColorInt int color) {
        return String.format(Locale.US, ARGB_FORMAT, color);
    }

    /**
     * Convert a color to an RGB string.
     * <p>i.e. {@link Color#BLUE Color.BLUE} returns the string {@code #0000FF}.</p>
     * @param color a color
     * @return the color as string, or null if {@code color} is {@code null}.
     */
    public static String toRGBString(@ColorInt int color) {
        return String.format(Locale.US, RGB_FORMAT, 0xFFFFFF & color);
    }

    /**
     * Convert a color to an HSV string.
     * <p>i.e. {@link Color#BLUE Color.BLUE} returns the string {@code hsv[240.00, 1.00, 1.00]}.</p>
     * @param color a color
     * @return the color as a HSV string.
     */
    public static String toHsvString(int color) {
        return toHsvString(toHSV(color));
    }

    /**
     * Convert a HSV {@code float[]} to an HSV string.
     * <p>i.e. {@link Color#BLUE Color.BLUE} returns the string {@code hsv[240.00, 1.00, 1.00]}.</p>
     * @param hsv a HSV {@code float[]}
     * @return the HSV as a HSV string.
     */
    public static String toHsvString(float[] hsv) {
        return String.format(Locale.US, HSV_FORMAT, hsv[0], hsv[1], hsv[2]);
    }

    public static float[] toHSV(@ColorInt int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        return hsv;
    }

    /**
     * Set the alpha component of {@code color} to be {@code alpha}.
     *
     * @param color A color
     * @param alpha The new alpha value for the color, in the range 0 - 255.
     * @return A the given {@code color}, with the given {@code alpha}
     */
    @ColorInt
    public static int setAlphaComponent(@ColorInt int color,
                                        @IntRange(from = 0x0, to = 0xFF) int alpha) {
        return android.support.v4.graphics.ColorUtils.setAlphaComponent(color, alpha);
    }

    /**
     * Returns the luminance of a color in the range {code 0d} for darkest black, to {@code 1d} for brightest white.
     *
     * <p>Formula defined here: http://www.w3.org/TR/2008/REC-WCAG20-20081211/#relativeluminancedef</p>
     *
     * @param color A color to test
     * @return the luminance of a color
     */
    public static double calculateLuminance(@ColorInt int color) {
        return android.support.v4.graphics.ColorUtils.calculateLuminance(color);
    }

    /**
     * Returns the contrast ratio between {@code foreground} and {@code background}.
     * {@code background} must be opaque.
     *
     * <p>Formula defined <a href="http://www.w3.org/TR/2008/REC-WCAG20-20081211/#contrast-ratiodef">here</a>.</p>
     *
     * @param foreground A color
     * @param background A color
     * @return the contrast ratio between {@code foreground} and {@code background}.
     */
    public static double calculateContrast(@ColorInt int foreground, @ColorInt int background) {
        return android.support.v4.graphics.ColorUtils.calculateContrast(foreground, background);
    }

    /**
     * Calculates the minimum alpha value which can be applied to {@code foreground} so that would
     * have a contrast value of at least {@code minContrastRatio} when compared to
     * {@code background}.
     *
     * @param foreground       the foreground color.
     * @param background       the background color. Should be opaque.
     * @param minContrastRatio the minimum contrast ratio.
     * @return the alpha value in the range 0-255, or -1 if no value could be calculated.
     */
    public static int calculateMinimumAlpha(@ColorInt int foreground, @ColorInt int background, float minContrastRatio) {
        return android.support.v4.graphics.ColorUtils.calculateMinimumAlpha(foreground, background, minContrastRatio);
    }

}
