package com.shopgun.android.utils.log;

import android.util.Log;

public class LogCatLogger implements Logger {

    private static final String CHUNK_FORMAT = "[chunk %s/%s] %s";

    private final int mMinLevel;

    public LogCatLogger() {
        this(Log.DEBUG);
    }

    public LogCatLogger(int minLevel) {
        mMinLevel = minLevel;
    }

    @Override
    public int v(String tag, String msg) {
        return log(Log.VERBOSE, tag, msg);
    }

    @Override
    public int v(String tag, String msg, Throwable tr) {
        return log(Log.VERBOSE, tag, msg, tr);
    }

    @Override
    public int d(String tag, String msg) {
        return log(Log.DEBUG, tag, msg);
    }

    @Override
    public int d(String tag, String msg, Throwable tr) {
        return log(Log.DEBUG, tag, msg, tr);
    }

    @Override
    public int i(String tag, String msg) {
        return log(Log.INFO, tag, msg);
    }

    @Override
    public int i(String tag, String msg, Throwable tr) {
        return log(Log.INFO, tag, msg, tr);
    }

    @Override
    public int w(String tag, String msg) {
        return log(Log.WARN, tag, msg);
    }

    @Override
    public int w(String tag, String msg, Throwable tr) {
        return log(Log.WARN, tag, msg, tr);
    }

    @Override
    public int e(String tag, String msg) {
        return log(Log.ERROR, tag, msg);
    }

    @Override
    public int e(String tag, String msg, Throwable tr) {
        return log(Log.ERROR, tag, msg, tr);
    }

    private int log(int level, String tag, String msg) {
        return log(level, tag, msg, null);
    }

    private int log(int level, String tag, String msg, Throwable tr) {

        synchronized (LogCatLogger.class) {

            if (level < mMinLevel) {
                return 0;
            }

            if (msg.length() > 4000) {

                int bytesWritten = 0;
                int chunkCount = msg.length() / 4000;
                for (int i = 0; i <= chunkCount; i++) {
                    int max = 4000 * (i + 1);
                    int end = (max >= msg.length()) ? msg.length() : max;
                    String msgChunk = String.format(CHUNK_FORMAT, i, chunkCount, msg.substring(4000 * i, end));
                    if (i == chunkCount && tr != null) {
                        bytesWritten += logToLogCat(level, tag, msgChunk, tr);
                    } else {
                        bytesWritten += logToLogCat(level, tag, msgChunk);
                    }
                }
                return bytesWritten;

            }

            if (tr != null) {
                return logToLogCat(level, tag, msg, tr);
            }

            return logToLogCat(level, tag, msg);

        }

    }

    private int logToLogCat(int level, String tag, String msg) {
        switch (level) {
            case Log.VERBOSE:
                return Log.v(tag, msg);
            case Log.DEBUG:
                return Log.d(tag, msg);
            case Log.INFO:
                return Log.i(tag, msg);
            case Log.WARN:
                return Log.w(tag, msg);
            case Log.ERROR:
                return Log.e(tag, msg);
            default:
                throw new IllegalArgumentException("Invalid log level: " + level +
                        ". Log level must be in the range [2-6], see android.util.Log for more info");
        }
    }

    private int logToLogCat(int level, String tag, String msg, Throwable tr) {
        switch (level) {
            case Log.VERBOSE:
                return Log.v(tag, msg, tr);
            case Log.DEBUG:
                return Log.d(tag, msg, tr);
            case Log.INFO:
                return Log.i(tag, msg, tr);
            case Log.WARN:
                return Log.w(tag, msg, tr);
            case Log.ERROR:
                return Log.e(tag, msg, tr);
            default:
                throw new IllegalArgumentException("Invalid log level: " + level +
                        ". Log level must be in the range [2-6], see android.util.Log for more info");
        }
    }

}
