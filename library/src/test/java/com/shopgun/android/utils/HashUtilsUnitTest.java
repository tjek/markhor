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

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class HashUtilsUnitTest {

    private static final String MD5_REGEX = "^[a-fA-Z0-9]{32}$";
    private static final String SHA256_REGEX = "^[a-fA-F0-9]{64}$";

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testMd5() throws Exception {

        String emptyString = HashUtils.md5("");
        Assert.assertNotNull(emptyString);
        Assert.assertTrue(emptyString.matches(MD5_REGEX));

        String regularString = HashUtils.md5("md5 must always follow the regex constraint");
        Assert.assertNotNull(regularString);
        Assert.assertTrue(regularString.matches(MD5_REGEX));

        Assert.assertEquals("2897ed14d78abd158165e462370719c0", HashUtils.md5("test my string").toLowerCase());
        Assert.assertEquals("3bcfa45c6c474a44644b67690ba6209f", HashUtils.md5("test my string\\nwithnewline").toLowerCase());

    }

    @Test
    public void testSha256() throws Exception {

        String emptyString = HashUtils.sha256("");
        Assert.assertNotNull(emptyString);
        Assert.assertTrue(emptyString.matches(SHA256_REGEX));

        String regularString = HashUtils.sha256("sha256 must always follow the regex constraint");
        Assert.assertNotNull(regularString);
        Assert.assertTrue(regularString.matches(SHA256_REGEX));

        Assert.assertEquals("4e02b43613429b39c7eebf7d9673c1cc88f1d02862e6586ccb47367b0bf50ad4",
                HashUtils.sha256("test my string").toLowerCase());
        Assert.assertEquals("aaae559c5335370994be18668877907a3520b6c3964279c6183039a15a39cb2d",
                HashUtils.sha256("test my string\\nwithnewline").toLowerCase());
    }

}