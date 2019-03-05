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

import androidx.annotation.NonNull;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {

    public static final String TAG = Tag.from(HashUtils.class);

    private final static char[] HEX_CHARS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    private HashUtils() {
        // private constructor
    }

    /**
     * Generate a SHA256 checksum of a string.
     *
     * @param string to SHA256
     * @return A SHA256 string, or {@code null} if a valid SHA256 couldn't be generated
     */
    public static String sha256(@NonNull String string) {
        return digest(string, "SHA-256", "UTF-8");
    }

    /**
     * Generate a MD5 checksum of a string.
     *
     * @param string to generate a checksum from
     * @return A MD5 string, or {@code null} if a valid MD5 couldn't be generated
     */
    public static String md5(@NonNull String string) {
        return digest(string, "MD5", "UTF-8");
    }

    private static String digest(String string, String digestAlgo, String encoding) {
        try {
            MessageDigest digester = MessageDigest.getInstance(digestAlgo);
            digester.update(string.getBytes(encoding));
            return hex(digester.digest());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String hex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int value = bytes[i] & 0xFF;
            hexChars[i * 2] = HEX_CHARS[value >>> 4];
            hexChars[i * 2 + 1] = HEX_CHARS[value & 0x0F];
        }
        return new String(hexChars);
    }

}
