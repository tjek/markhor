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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class TextUtilsUnitTest {

    @Test
    public void testJoin() throws Exception {
        assertNotEquals(null, TextUtils.join(",", new int[]{}));
        assertEquals("", TextUtils.join(",", new int[]{}));
        assertEquals("1,2,3", TextUtils.join(",", new int[]{1,2,3}));
    }

    @Test
    public void testEmpty() throws Exception {
        assertTrue(TextUtils.isEmpty(null));
        assertTrue(TextUtils.isEmpty(""));
        assertFalse(TextUtils.isEmpty(" "));
        assertFalse(TextUtils.isEmpty("some string"));
    }

    @Test
    public void testCamelCase() throws Exception {

        String[] testArray =        {null, "", "a", "an", "an example string", "AN EXAMPLE STRING", "AN EXAMPLE string", "An Example String"};
        String[] firstChar =        {null, "", "A", "An", "An example string", "An example string", "An example string", "An example string"};
        String[] allWords =         {null, "", "A", "An", "An Example String", "An Example String", "An Example String", "An Example String"};
        String[] upperToCamelCase = {null, "", "a", "an", "an example string", "An Example String", "AN EXAMPLE string", "An Example String"};

        for (int i = 0; i < testArray.length; i++) {
            String s = testArray[i];
            assertEquals(firstChar[i], TextUtils.toCamelCase(s));
            assertEquals(allWords[i], TextUtils.toCamelCase(s, " "));
            assertEquals(upperToCamelCase[i], TextUtils.toCamelCaseIfAllUppercase(s, " "));
        }

    }

}