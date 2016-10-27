package com.shopgun.android.utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ParcelableUtils {

    public static final String TAG = ParcelableUtils.class.getSimpleName();

    private ParcelableUtils() {
        //no instance
    }
    
    /**
     * Create a deep copy of any {@link Parcelable} implementation.
     *
     * @param obj     An object to clone
     * @param creator The creator to clone from
     * @param <T> Any type
     * @return A clone of the obj
     */
    public static <T extends Parcelable> T copyParcelable(T obj, Parcelable.Creator<T> creator) {
        Parcel parcel = Parcel.obtain();
        obj.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        return creator.createFromParcel(parcel);
    }

    /**
     * Create a deep copy of any {@link List} containing {@link Parcelable}
     *
     * @param list    A list to clone
     * @param creator The creator to clone from
     * @param <T> Any type
     * @return A cloned list
     */
    public static <T extends Parcelable> List<T> copyParcelable(List<T> list, Parcelable.Creator<T> creator) {
        ArrayList<T> tmp = new ArrayList<T>();
        for (T t : list) {
            tmp.add(copyParcelable(t, creator));
        }
        return tmp;
    }

}
