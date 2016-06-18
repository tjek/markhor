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

import java.util.Date;
import java.util.concurrent.TimeUnit;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class DateUtilsUnitTest {

    @Test
    public void testMidnight() throws Exception {
        long day = TimeUnit.DAYS.toMillis(1);

        Assert.assertTrue(DateUtils.isMidnight(new Date(day)));
        Assert.assertTrue(DateUtils.isMidnight(new Date(day), 0));
        Assert.assertTrue(DateUtils.isMidnight(new Date(day), 0, 0));

        long secondToMidnight = day - 1000;
        Assert.assertFalse(DateUtils.isMidnight(new Date(secondToMidnight)));
        Assert.assertFalse(DateUtils.isMidnight(new Date(secondToMidnight), 0));
        Assert.assertFalse(DateUtils.isMidnight(new Date(secondToMidnight), 0, 0));

        Assert.assertTrue(DateUtils.isMidnight(new Date(secondToMidnight), 1000));
        Assert.assertTrue(DateUtils.isMidnight(new Date(secondToMidnight), 1000, 0));

        long secondAfterMidnight = day + 1000;
        Assert.assertFalse(DateUtils.isMidnight(new Date(secondAfterMidnight), 0, 0));
        Assert.assertTrue(DateUtils.isMidnight(new Date(secondAfterMidnight), 0, 1000));

    }

    @Test
    public void testRound() {

    }

}