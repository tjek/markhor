package com.shopgun.android.utils.log;

/**
 * Static class for easy access to logging with {@link Logger}
 */
public class L {

    private static volatile Logger mLogger = new LogCatLogger(0);

    public static void setLogger(Logger logger) {
        mLogger = (logger == null ? new LogCatLogger(0) : logger);
    }

    public static int v(String tag, String msg) {
        return mLogger.v(tag, msg);
    }

    public static int v(String tag, String msg, Throwable tr) {
        return mLogger.v(tag, msg, tr);
    }

    public static int d(String tag, String msg) {
        return mLogger.d(tag, msg);
    }

    public static int d(String tag, String msg, Throwable tr) {
        return mLogger.d(tag, msg, tr);
    }

    public static int i(String tag, String msg) {
        return mLogger.i(tag, msg);
    }

    public static int i(String tag, String msg, Throwable tr) {
        return mLogger.i(tag, msg, tr);
    }

    public static int w(String tag, String msg) {
        return mLogger.w(tag, msg);
    }

    public static int w(String tag, String msg, Throwable tr) {
        return mLogger.w(tag, msg, tr);
    }

    public static int e(String tag, String msg) {
        return mLogger.e(tag, msg);
    }

    public static int e(String tag, String msg, Throwable tr) {
        return mLogger.e(tag, msg, tr);
    }

}
