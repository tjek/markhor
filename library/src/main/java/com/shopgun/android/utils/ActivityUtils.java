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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class ActivityUtils {

    public static final String TAG = Tag.from(ActivityUtils.class);

    private ActivityUtils() {
        // private constructor
    }

    /**
     * Quick way of restarting your activity/application
     * @param a An activity
     */
    public static void restart(Activity a) {
        Context c = a.getApplicationContext();
        Intent i = c.getPackageManager().getLaunchIntentForPackage(c.getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        a.finish();
        a.startActivity(i);
    }

}
