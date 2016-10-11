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

public class NumberUtils {

    public static final float FLOAT_EPSILON =   0.001f;
    public static final double DOUBLE_EPSILON = 0.001d;

    /**
     * Method for detecting if two floats are almost equal (epsilon {@link NumberUtils#FLOAT_EPSILON})
     * Inconsistencies are to be expected, due to the nature of float values in java
     *
     * @param lhs  a float
     * @param rhs another float
     * @return true if equal, else false
     */
    public static boolean isEqual(float lhs, float rhs) {
        return isEqual(lhs, rhs, FLOAT_EPSILON);
    }

    /**
     * Method for detecting if two floats are almost equal
     * Inconsistencies are to be expected, due to the nature of float values in java
     *
     * @param lhs   a float
     * @param rhs  another float
     * @param epsilon The precision of the measurement
     * @return true if equal, else false
     */
    public static boolean isEqual(float lhs, float rhs, float epsilon) {
        return Float.compare(lhs, rhs) == 0 || Math.abs(lhs - rhs) <= epsilon;
    }

    /**
     * Method for detecting if two floats are almost equal (epsilon {@link NumberUtils#DOUBLE_EPSILON})
     * Inconsistencies are to be expected, due to the nature of double values in java
     *
     * @param lhs  a float
     * @param rhs another float
     * @return true if equal, else false
     */
    public static boolean isEqual(double lhs, double rhs) {
        return isEqual(lhs, rhs, DOUBLE_EPSILON);
    }

    /**
     * Method for detecting if two floats are almost equal
     * Inconsistencies are to be expected, due to the nature of double values in java
     *
     * @param lhs   a float
     * @param rhs  another float
     * @param epsilon The precision of the measurement
     * @return true if equal, else false
     */
    public static boolean isEqual(double lhs, double rhs, double epsilon) {
        return Double.compare(lhs, rhs) == 0 || Math.abs(lhs - rhs) <= epsilon;
    }

    /**
     * Clamp the current value in between a min and max value.
     * @param min the lower bound
     * @param current value to check
     * @param max the upper bound
     * @return {@code min} if {@code current} if less than {@code min},
     * or {@code max} if {@code current} is greater than {@code max},
     * else {@code current}.
     */
    public static float clamp(float min, float current, float max) {
        return Math.max(min, Math.min(current, max));
    }

    /**
     * Clamp the current value in between a min and max value.
     * @param min the lower bound
     * @param current value to check
     * @param max the upper bound
     * @return {@code min} if {@code current} if less than {@code min},
     * or {@code max} if {@code current} is greater than {@code max},
     * else {@code current}.
     */
    public static long clamp(long min, long current, long max) {
        return Math.max(min, Math.min(current, max));
    }

    /**
     * Clamp the current value in between a min and max value.
     * @param min the lower bound
     * @param current value to check
     * @param max the upper bound
     * @return {@code min} if {@code current} if less than {@code min},
     * or {@code max} if {@code current} is greater than {@code max},
     * else {@code current}.
     */
    public static int clamp(int min, int current, int max) {
        return Math.max(min, Math.min(current, max));
    }

    /**
     * Clamp the current value in between a min and max value.
     * @param min the lower bound
     * @param current value to check
     * @param max the upper bound
     * @return {@code min} if {@code current} if less than {@code min},
     * or {@code max} if {@code current} is greater than {@code max},
     * else {@code current}.
     */
    public static double clamp(double min, double current, double max) {
        return Math.max(min, Math.min(current, max));
    }

}
