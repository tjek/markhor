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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConnectivityUtilsUnitTest {

    @Mock Context mMockContext;
    @Mock ConnectivityManager mMockConnectivityManager;
    @Mock NetworkInfo mMockNetworkInfo;

    @Test
    public void testIsOnline() throws Exception {

        Mockito.when(mMockContext.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(mMockConnectivityManager);

        // If NetworkInfo is null, it's false
        Mockito.when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(null);
        Assert.assertFalse(ConnectivityUtils.isOnline(mMockContext));

        // Apply correct NetworkInfo
        Mockito.when(mMockConnectivityManager.getActiveNetworkInfo()).thenReturn(mMockNetworkInfo);

        // base case for true
        Mockito.when(mMockNetworkInfo.isConnected()).thenReturn(true);
        Assert.assertTrue(ConnectivityUtils.isOnline(mMockContext));

        // base case for false
        Mockito.when(mMockNetworkInfo.isConnected()).thenReturn(false);
        Assert.assertFalse(ConnectivityUtils.isOnline(mMockContext));

    }

}