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
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.shopgun.android.utils.enums.Orientation;

public class DisplayUtils {

    public static final String TAG = Tag.from(DisplayUtils.class);

    private DisplayUtils() {
        // private constructor
    }

    public static int getScreenWidth(Context ctx) {
        return getDisplayMetrics(ctx).widthPixels;
    }

    public static int getScreenHeight(Context ctx) {
        return getDisplayMetrics(ctx).heightPixels;
    }

    private static WindowManager getWindowManager(Context ctx) {
        return (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
    }

    private static DisplayMetrics getDisplayMetrics(Context ctx) {
        Display display = getWindowManager(ctx).getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        return outMetrics;
    }

    public static boolean isPortrait(Context ctx) {
        return Orientation.fromContext(ctx).isPortrait();
    }

    public static boolean isLandscape(Context ctx) {
        return Orientation.fromContext(ctx).isLandscape();
    }

}
