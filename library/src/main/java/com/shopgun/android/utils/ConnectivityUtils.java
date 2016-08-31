package com.shopgun.android.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectivityUtils {

    public static final String TAG = Tag.from(ConnectivityUtils.class);

    private ConnectivityUtils() {
        // private constructor
    }

    /**
     * Method for determining the current network state
     * @return true if network connectivity exists, false otherwise.
     */
    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

}
