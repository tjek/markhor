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

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class NumberUtilsUnitTest {

    @Test
    public void testEquality() throws Exception {

        Assert.assertTrue(NumberUtils.isEqual(Float.NaN, Float.NaN));
        Assert.assertFalse(NumberUtils.isEqual(1f, Float.NaN));
        Assert.assertTrue(NumberUtils.isEqual(1f, 1f));
        Assert.assertTrue(NumberUtils.isEqual(1.001f, 1.002f));
        Assert.assertFalse(NumberUtils.isEqual(1.01f, 1.02f));
        Assert.assertTrue(NumberUtils.isEqual(1.1f, 1.2f, 1));

        Assert.assertTrue(NumberUtils.isEqual(Double.NaN, Double.NaN));
        Assert.assertFalse(NumberUtils.isEqual(1d, Double.NaN));
        Assert.assertTrue(NumberUtils.isEqual(1d, 1d));
        Assert.assertTrue(NumberUtils.isEqual(1.0001d, 1.0002d));
        Assert.assertFalse(NumberUtils.isEqual(1.001d, 1.002d));
        Assert.assertTrue(NumberUtils.isEqual(1.1d, 1.2d, 1));

    }

}