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

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    public static final String TAG = Tag.from(DateUtils.class);
    private static final long DAY_IN_MILLIS = TimeUnit.DAYS.toMillis(1);

    private DateUtils() {
        // private
    }

    public static boolean isMidnight(Date d) {
        return isMidnight(d, 0, 0);
    }

    public static boolean isMidnight(Date d, long delta) {
        return isMidnight(d, delta, delta);
    }

    public static boolean isMidnight(Date date, long toMidnightDelta, long afterMidnightDelta) {
        long remainder = date.getTime() % DAY_IN_MILLIS;
        return (DAY_IN_MILLIS-toMidnightDelta) <= remainder || remainder <= afterMidnightDelta;
    }

    /**
     * <p>Method for rounding the time (date in milliseconds) down to the nearest second. This is necessary when
     * comparing timestamps between the server and client, as the server uses seconds, and timestamps will rarely match
     * as expected otherwise.</p>
     *
     * {@code 1394021345625 -> 1394021345000}
     *
     * @param date A date to round
     * @return A date, floored to nearest second.
     */
    public static Date roundTime(Date date) {
        if (date != null) {
            date.setTime(1000 * (date.getTime() / 1000));
        }
        return date;
    }

}
