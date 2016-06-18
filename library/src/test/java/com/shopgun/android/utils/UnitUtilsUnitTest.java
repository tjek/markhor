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
import android.content.res.Resources;
import android.util.DisplayMetrics;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UnitUtilsUnitTest {

    private static final float DENSITY = 3.5f;
    private static final int DIPS = 100;

    @Mock
    Context mMockContext;

    @Mock
    Resources mMockResources;

    @Mock
    DisplayMetrics mMockDisplayMetrics;

    @Test
    public void testPxDpConversion() throws Exception {

        Mockito.when(mMockContext.getResources()).thenReturn(mMockResources);
        Mockito.when(mMockResources.getDisplayMetrics()).thenReturn(mMockDisplayMetrics);
        mMockDisplayMetrics.density = DENSITY;

        // convert to pixels
        int actualPx = UnitUtils.dpToPx(DIPS, mMockContext);
        Assert.assertEquals((int)(DIPS*DENSITY), actualPx);

        // convert back to dips
        int actualDp = UnitUtils.pxToDp(actualPx, mMockContext);
        Assert.assertEquals(DIPS, actualDp);

    }

    @Test
    public void testHumanReadableByteCount() throws Exception {

        Assert.assertEquals("0 B", UnitUtils.humanReadableByteCount(0, true));
        Assert.assertEquals("0 B", UnitUtils.humanReadableByteCount(0, false));

        Assert.assertEquals("27 B", UnitUtils.humanReadableByteCount(27, true));
        Assert.assertEquals("27 B", UnitUtils.humanReadableByteCount(27, false));

        Assert.assertEquals("1.0 kB", UnitUtils.humanReadableByteCount(1023, true));
        Assert.assertEquals("1023 B", UnitUtils.humanReadableByteCount(1023, false));

        Assert.assertEquals("1.0 kB", UnitUtils.humanReadableByteCount(1024, true));
        Assert.assertEquals("1.0 KiB", UnitUtils.humanReadableByteCount(1024, false));

    }

}