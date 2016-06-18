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

package com.shopgun.android.utils.enums;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcel;
import android.os.Parcelable;

import com.shopgun.android.utils.DisplayUtils;

public enum Orientation implements Parcelable {

    PORTRAIT, LANDSCAPE;

    /**
     * Determines the current {@link Orientation} from a given {@link Configuration}.
     * <p>if the given {@link Context} does not reveal the device orientation,
     * we will fallback to using screen dimensions as the determining factor</p>
     * @param ctx a context
     * @return the current orientation for this device
     */
    public static Orientation fromContext(Context ctx) {
        Orientation o = internalFromConfiguration(ctx.getResources().getConfiguration());
        if (o == null) {
            int height = DisplayUtils.getScreenHeight(ctx);
            int width = DisplayUtils.getScreenWidth(ctx);
            o = (height > width) ? PORTRAIT : LANDSCAPE;
        }
        return o;
    }

    /**
     * Determines the current {@link Orientation} from a given {@link Configuration}.
     * <p>If the orientation cannot be determined from the given {@link Configuration}
     * we will default to {@link Orientation#PORTRAIT} as most android devices are still
     * mobile devices, usually in portrait mode.</p>
     * @param config a configuration
     * @return the current orientation for this device
     */
    public static Orientation fromConfiguration(Configuration config) {
        Orientation o = internalFromConfiguration(config);
        return o == null ? PORTRAIT : o;
    }

    private static Orientation internalFromConfiguration(Configuration config) {
        if (config != null) {
            switch (config.orientation) {
                case Configuration.ORIENTATION_LANDSCAPE:
                    return LANDSCAPE;
                case Configuration.ORIENTATION_PORTRAIT:
                    return PORTRAIT;
            }
        }
        return null;
    }

    /**
     * @return {@code true} if this {@link Orientation} is landscape, else {@code false}
     */
    public boolean isLandscape() {
        return Orientation.this == LANDSCAPE;
    }

    /**
     * @return {@code true} if {@link Orientation} is portrait, else {@code false}
     */
    public boolean isPortrait() {
        return Orientation.this == PORTRAIT;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name());
    }

    public static final Creator<Orientation> CREATOR = new Creator<Orientation>() {
        @Override
        public Orientation createFromParcel(final Parcel source) {
            return Orientation.valueOf(source.readString());
        }

        @Override
        public Orientation[] newArray(final int size) {
            return new Orientation[size];
        }
    };

}
