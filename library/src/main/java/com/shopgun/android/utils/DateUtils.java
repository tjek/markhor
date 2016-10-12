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

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    public static final String TAG = Tag.from(DateUtils.class);
    private static final long DAY_IN_MILLIS = TimeUnit.DAYS.toMillis(1);

    private DateUtils() {
        // private
    }

    /**
     * Determine if the time of day of a given {@link Date} is at midnight. e.g.:
     *
     * <ul>
     *      <li>"2016-06-29T00:00:00+0000" == {@code true}</li>
     *      <li>"2016-06-29T00:00:01+0000" == {@code false}</li>
     *      <li>"2016-06-29T23:59:59+0000" == {@code false}</li>
     * </ul>
     *
     * @param date a date
     * @return {@code true} if the time of day is at midnight, else {@code false}
     */
    public static boolean isMidnight(Date date) {
        return isMidnight(date, 0, 0);
    }

    /**
     * Determine if the time of day of a given {@link Date} is at midnight. The time doesn't have to
     * be a strict match to midnight, you cen control this by increasing the {@code epsilon}, which
     * is symmetrical on each side of midnight. e.g. the results with an epsilon of {@code 1000} is:
     *
     * <ul>
     *      <li>"2016-06-29T00:00:00+0000" == {@code true}</li>
     *      <li>"2016-06-29T00:00:01+0000" == {@code true}</li>
     *      <li>"2016-06-29T23:59:59+0000" == {@code true}</li>
     *      <li>"2016-06-29T00:00:01+0500" == {@code false}</li>
     *      <li>"2016-06-29T23:59:58+0500" == {@code false}</li>
     * </ul>
     *
     * @param date a date
     * @param epsilon the amount of time to flex from actual midnight
     * @return {@code true} if the time of day is at midnight (or within the given epsilon bounds), else {@code false}
     */
    public static boolean isMidnight(Date date, long epsilon) {
        return isMidnight(date, epsilon, epsilon);
    }

    /**
     * Determine if the time of day of a given {@link Date} is at midnight. The time doesn't have to
     * be a strict match to midnight, you cen control this by increasing the {@code epsilon} on each
     * each 'side' of midnight. e.g. the results with an epsilon of {@code 1000} on both 'sides' of
     * midnight is:
     *
     * <ul>
     *      <li>"2016-06-29T00:00:00+0000" == {@code true}</li>
     *      <li>"2016-06-29T00:00:01+0000" == {@code true}</li>
     *      <li>"2016-06-29T23:59:59+0000" == {@code true}</li>
     *      <li>"2016-06-29T00:00:01+0500" == {@code false}</li>
     *      <li>"2016-06-29T23:59:58+0500" == {@code false}</li>
     * </ul>
     *
     * @param date a date
     * @param epsilonToMidnight the amount of time to flex prior to midnight
     * @param epsilonAfterMidnight the amount of time to flex past midnight
     * @return {@code true} if the time of day is at midnight (or within the given epsilon bounds), else {@code false}
     */
    public static boolean isMidnight(Date date, long epsilonToMidnight, long epsilonAfterMidnight) {
        long remainder = getTimeOfDayWithTimeZone(date) % DAY_IN_MILLIS;
        return (DAY_IN_MILLIS-epsilonToMidnight) <= remainder || remainder <= epsilonAfterMidnight;
    }

    /**
     * Calculates the time of day (in milliseconds) of the {@code date} given.
     * <p>e.g. the date {@code 2016-06-13 01:00am} will result in {@code 3600000}</p>
     * @param date a date
     * @return the time of day
     */
    public static long getTimeOfDayWithTimeZone(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int h = c.get(Calendar.HOUR_OF_DAY);
        int m = c.get(Calendar.MINUTE);
        int s = c.get(Calendar.SECOND);
        return TimeUnit.HOURS.toMillis(h) + TimeUnit.MINUTES.toMillis(m)+ TimeUnit.SECONDS.toMillis(s);
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
