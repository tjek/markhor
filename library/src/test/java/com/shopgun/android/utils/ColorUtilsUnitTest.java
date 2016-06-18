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

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class ColorUtilsUnitTest {

    @Test
    public void testColorUtils() throws Exception {

        Assert.assertEquals(0xFFFFFFFF, ColorUtils.getCompliment(0xFF000000));
        Assert.assertEquals(0xFF0000FF, ColorUtils.getCompliment(0xFFFFFF00));

//        Assert.assertNull(ColorUtils.toARGBString(null));
//        Assert.assertEquals("#FF000000", ColorUtils.toARGBString(new MaterialColorImpl(Color.BLACK)));
//        Assert.assertEquals("#FF0000FF", ColorUtils.toARGBString(new MaterialColorImpl(Color.BLUE)));
//        Assert.assertEquals("#FFFF0000", ColorUtils.toARGBString(new MaterialColorImpl(Color.RED)));
//        Assert.assertEquals("#FF00FF00", ColorUtils.toARGBString(new MaterialColorImpl(Color.GREEN)));

        Assert.assertEquals("#00000000", ColorUtils.toARGBString(Color.TRANSPARENT));
        Assert.assertEquals("#FF000000", ColorUtils.toARGBString(Color.BLACK));
        Assert.assertEquals("#FF0000FF", ColorUtils.toARGBString(Color.BLUE));
        Assert.assertEquals("#FFFF0000", ColorUtils.toARGBString(Color.RED));
        Assert.assertEquals("#FF00FF00", ColorUtils.toARGBString(Color.GREEN));

        Assert.assertEquals("#000000", ColorUtils.toRGBString(Color.TRANSPARENT));
        Assert.assertEquals("#000000", ColorUtils.toRGBString(Color.BLACK));
        Assert.assertEquals("#0000FF", ColorUtils.toRGBString(Color.BLUE));
        Assert.assertEquals("#FF0000", ColorUtils.toRGBString(Color.RED));
        Assert.assertEquals("#00FF00", ColorUtils.toRGBString(Color.GREEN));

        assertFloatArrayEquals(new float[]{0.0f, 1.0f, 1.0f}, ColorUtils.toHSV(0xFFFF0000));
        assertFloatArrayEquals(new float[]{240.0f, 1.0f, 1.0f}, ColorUtils.toHSV(0xFF0000FF));
        assertFloatArrayEquals(new float[]{60.0f, 1.0f, 1.0f}, ColorUtils.toHSV(0xFFFFFF00));

//        Assert.assertEquals("hsv[0.00, 1.00, 1.00]", ColorUtils.toHsvString(new MaterialColorImpl(Color.RED)));
        Assert.assertEquals("hsv[0.00, 1.00, 1.00]", ColorUtils.toHsvString(Color.RED));
        Assert.assertEquals("hsv[0.00, 1.00, 1.00]", ColorUtils.toHsvString(new float[]{0.0f, 1.0f, 1.0f}));

        Assert.assertEquals(Color.TRANSPARENT, ColorUtils.setAlphaComponent(Color.BLACK, 0));
        Assert.assertEquals(Color.argb(0, 255, 0, 0), ColorUtils.setAlphaComponent(Color.RED, 0));
        Assert.assertEquals(Color.argb(120, 0, 255, 0), ColorUtils.setAlphaComponent(Color.GREEN, 120));

        Assert.assertEquals(1d, ColorUtils.calculateContrast(Color.WHITE, Color.WHITE));
        Assert.assertEquals(1d, ColorUtils.calculateContrast(Color.BLACK, Color.BLACK));
        Assert.assertEquals(21d, ColorUtils.calculateContrast(Color.WHITE, Color.BLACK));
        Assert.assertEquals(21d, ColorUtils.calculateContrast(Color.BLACK, Color.WHITE));

        Assert.assertEquals(0d, ColorUtils.calculateLuminance(Color.BLACK));
        Assert.assertEquals(0.215d, ColorUtils.calculateLuminance(Color.argb(255, 128,128,128)), 0.001d);
        Assert.assertEquals(1d, ColorUtils.calculateLuminance(Color.WHITE));

        System.out.println("MinimumAlpha(white.black.21): " + ColorUtils.calculateMinimumAlpha(Color.WHITE, Color.BLACK, 21));
        System.out.println("MinimumAlpha(white.black.21): " + ColorUtils.calculateMinimumAlpha(Color.RED, Color.GREEN, 5));
        System.out.println("MinimumAlpha(white.black.1): " + ColorUtils.calculateMinimumAlpha(Color.WHITE, Color.BLACK, 21));

    }

    public static void assertFloatArrayEquals(float[] expected, float[] actual) {
        assertFloatArrayEquals(expected, actual, 0.01f);
    }

    public static void assertFloatArrayEquals(float[] expected, float[] actual, float delta) {
        Assert.assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], actual[i], delta);
        }
    }

}
