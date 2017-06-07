package com.shopgun.android.utils.log;

import android.content.Context;
import android.util.Log;

import com.shopgun.android.utils.FileUtils;
import com.shopgun.android.utils.ToStringUtils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DiskLogger implements Logger {

    public static final String TAG = DiskLogger.class.getSimpleName();

    public static final String DEF_LOG_DIR = "log";
    public static final String DEF_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss:SSSZ";
    public static final String LOG_FORMAT = "%s %1.1s/%s: %s";


    private File mFile;
    private int mMinLevel;
    private DateFormat mDateFormat;

    public DiskLogger(Context context, String fileName) {
        this(context, fileName, Log.DEBUG);
    }

    public DiskLogger(Context context, String fileName, int minLevel) {
        this(context, fileName, minLevel, new SimpleDateFormat(DEF_DATE_FORMAT, Locale.US));
    }

    public DiskLogger(Context context, String fileName, int minLevel, DateFormat dateFormat) {
        this(new File(FileUtils.getExtDirectory(context, true, DEF_LOG_DIR), fileName), minLevel, dateFormat);
    }

    public DiskLogger(File file, int minLevel, DateFormat dateFormat) {
        mFile = file;
        mMinLevel = minLevel;
        mDateFormat = dateFormat;
    }

    @Override
    public int v(String tag, String msg) {
        return write(Log.VERBOSE, new Date(), tag, msg, null);
    }

    @Override
    public int v(String tag, String msg, Throwable tr) {
        return write(Log.VERBOSE, new Date(), tag, msg, tr);
    }

    @Override
    public int d(String tag, String msg) {
        return write(Log.DEBUG, new Date(), tag, msg, null);
    }

    @Override
    public int d(String tag, String msg, Throwable tr) {
        return write(Log.DEBUG, new Date(), tag, msg, tr);
    }

    @Override
    public int i(String tag, String msg) {
        return write(Log.INFO, new Date(), tag, msg, null);
    }

    @Override
    public int i(String tag, String msg, Throwable tr) {
        return write(Log.INFO, new Date(), tag, msg, tr);
    }

    @Override
    public int w(String tag, String msg) {
        return write(Log.WARN, new Date(), tag, msg, null);
    }

    @Override
    public int w(String tag, String msg, Throwable tr) {
        return write(Log.WARN, new Date(), tag, msg, tr);
    }

    @Override
    public int e(String tag, String msg) {
        return write(Log.ERROR, new Date(), tag, msg, null);
    }

    @Override
    public int e(String tag, String msg, Throwable tr) {
        return write(Log.ERROR, new Date(), tag, msg, tr);
    }

    synchronized int write(int logLevel, Date date, String tag, String msg, Throwable tr) {
        if (logLevel < mMinLevel || msg == null) {
            return 0;
        }

        StringBuilder sb = new StringBuilder();

        // create and append the log message
        String d = mDateFormat.format(date);
        String l = ToStringUtils.logLevelToString(logLevel);
        sb.append(String.format(Locale.US, LOG_FORMAT, d, l, tag, msg)).append("\n");

        // Append throwable info, if any
        if (tr != null) {
            sb.append(tr.getMessage());
            for (StackTraceElement ste : tr.getStackTrace()) {
                sb.append("- ").append(String.valueOf(ste)).append("\n");
            }
        }

        // write the message to a file
        String message = sb.toString();
        return FileUtils.writeToFile(mFile, message, true) ? message.getBytes().length : 0;
    }

}
