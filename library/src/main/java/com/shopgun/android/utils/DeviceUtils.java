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
import android.os.Build;

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

    public static String getRadio() {
        return Build.getRadioVersion();
    }

    /**
     * Get the build version of the device.
     * @return build version
     */
    public static String getBuildVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * Get the device manufacturer.
     * <p> A Samsung GT-P3110, will return "Samsung"
     * @return phone model
     */
    public static String getManufacturer() {
        return capitalize(android.os.Build.MANUFACTURER);
    }

    /**
     * Get the device model.
     * <p> A Samsung GT-P3110, will return "GT-P3110"
     * @return phone model
     */
    public static String getModel() {
        return android.os.Build.MODEL == null ?
                null :
                android.os.Build.MODEL.replace(
                        android.os.Build.MANUFACTURER, "")
                        .trim();
    }

    /**
     * Get the device model.
     * <p> A Samsung GT-P3110, will return "Samsung GT-P3110"
     * @return phone manufacturer and model
     */
    public static String getManufacturerAndModel() {
        String manufacturer = android.os.Build.MANUFACTURER;
        String model = android.os.Build.MODEL;
        if (model.startsWith(manufacturer)) {
            model = capitalize(model);
        } else {
            model = capitalize(manufacturer) + " " + model;
        }
        return model;
    }

    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

}
