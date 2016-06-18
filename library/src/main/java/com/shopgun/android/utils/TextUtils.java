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

import android.annotation.SuppressLint;

public class TextUtils {

    public static final String TAG = Tag.from(TextUtils.class);

    /**
     * Method for joining an array of int
     *
     * @param delimiter A string to join the int's by
     * @param tokens    the values
     * @return A formatted string
     */
    public static String join(CharSequence delimiter, int[] tokens) {
        StringBuilder sb = new StringBuilder();
        for (Object token : tokens) {
            if (sb.length() != 0) {
                sb.append(delimiter);
            }
            sb.append(token);
        }
        return sb.toString();
    }

    /**
     * Converts a string into it's camel case equivalent. But only if the String is all upper case.<br>
     * An example with 'space' (" ") as split:
     * <li>"AN EXAMPLE STRING" -> "An Example String"</li>
     * <li>"AN EXampLE STRING" -> "AN EXampLE STRING"</li>
     *
     * @param word A string to convert
     * @param splitChar  The regular expression to split the string on
     * @return A String
     */
    @SuppressLint("DefaultLocale")
    public static String toCamelCaseIfAllUppercase(String word, String splitChar) {

        if (isEmpty(word)) {
            return word;
        }

        if (word.toUpperCase().equals(word)) {
            return toCamelCase(word, splitChar);
        } else {
            return word;
        }

    }

    /**
     * Converts a string into it's camel case equivalent.<br>
     * An example with 'space' (" ") as split: "an example string" -> "An Example String".
     *
     * @param word  A string to convert
     * @param split The regular expression to split the string on
     * @return A new String in camel case
     */
    public static String toCamelCase(String word, String split) {

        if (isEmpty(word)) {
            return word;
        }

        split = (split == null ? " " : split);

        StringBuilder sb = new StringBuilder(word.length());
        for (String s : word.split(split)) {
            if (sb.length() > 0) {
                sb.append(split);
            }
            sb.append(toCamelCase(s));
        }
        return sb.toString();

    }

    /**
     * Convert a string (one word, no splitting), so the first Character is upper case,
     * and the rest (if any) is in lower case.
     *
     * @param word To convert
     * @return A new String with capitalized first letter, and the rest in lower case
     */
    @SuppressLint("DefaultLocale")
    public static String toCamelCase(String word) {

        if (isEmpty(word)) {
            return word;
        }

        char first = Character.toUpperCase(word.charAt(0));
        if (word.length() == 1) {
            return Character.toString(first);
        } else {
            return first + word.substring(1).toLowerCase();
        }

    }

    /**
     * Returns true if the string is null or 0-length.
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.length() == 0);
    }

}
