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

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamUtils {

    public static byte[] inputStreamToByte(InputStream is, int contentLength) throws IOException {

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            int read;
            if (contentLength <= 0) {
                contentLength = 0xffff;
            }
            byte[] data = new byte[contentLength];
            while ((read = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, read);
            }
        } finally {
            buffer.flush();
            is.close();
        }
        return buffer.toByteArray();
    }

    public static String inputStreamToString(InputStream is) throws IOException {
        return readerToString(is);
    }

    public static String readerToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } finally {
            is.close();
        }
        return sb.toString();
    }

}
