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

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

public class WindowUtils {

    public static final String TAG = Tag.from(WindowUtils.class);

    private WindowUtils() {
        // private
    }

    /**
     * Set the statusBar color in devices running {@link android.os.Build.VERSION_CODES#LOLLIPOP} and above
     * @param activity an activity
     * @param statusBarColor the color
     */
    @TargetApi(21)
    public static void setStatusBarColor(Activity activity, int statusBarColor) {
        setStatusBarColor(activity.getWindow(), statusBarColor);
    }

    /**
     * Set the statusBar color in devices running {@link android.os.Build.VERSION_CODES#LOLLIPOP} and above
     * @param window the window to apply the change to
     * @param statusBarColor the color
     */
    @TargetApi(21)
    public static void setStatusBarColor(Window window, int statusBarColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (statusBarColor == Color.BLACK && window.getNavigationBarColor() == Color.BLACK) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            } else {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            }
            window.setStatusBarColor(statusBarColor);
        }
    }

}
