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
import android.location.LocationManager;

import com.shopgun.android.utilities.R;

import java.util.List;

public class DeviceUtils {

    public static final String TAG = Tag.from(DeviceUtils.class);

    private DeviceUtils() {
        // private constructor
    }

    public static boolean isTablet(Context ctx) {
        return ctx.getResources().getBoolean(R.bool.isTablet);
    }

    public static boolean hasGPSSensor(Context context) {
        LocationManager mgr = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (mgr == null) {
            return false;
        }
        List<String> providers = mgr.getAllProviders();
        return providers != null && providers.contains(LocationManager.GPS_PROVIDER);
    }

}
