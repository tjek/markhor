package com.shopgun.android.utils.log;

public class LogUtil {

    public static final String TAG = LogUtil.class.getSimpleName();
    
    /**
     * Print the calling method by name and linenumber.
     * <p>com.shopgun.android.utils.MyClass.myMethod(MyClass.java:87)</p>
     */
    public static void printCallingMethod(Logger logger) {
        printMethod(logger, 4);
    }

    /**
     * Print the calling method by name and linenumber.
     * <p>com.shopgun.android.utils.MyClass.myMethod(MyClass.java:87)</p>
     *
     * @param element argument to easily evaluate it
     */
    public static void printMethod(Logger logger, int element) {
        StackTraceElement ste = Thread.currentThread().getStackTrace()[element];
        String clazzName = ste.getClassName();
        String tag = clazzName.substring(clazzName.lastIndexOf(".")+1, clazzName.length());
        String msg = ste.getMethodName() + "(" + ste.getFileName() + ":" + ste.getLineNumber() + ")";
        logger.d(tag, msg);
    }

    /**
     * Print a StackTrace from any given point of your source code.
     * Used to identify the source of a log message. It usually identifies the class or activity where the log call occurs.
     */
    public static void printStackTrace(Logger logger) {
        String tag = Thread.currentThread().getStackTrace()[3].getClass().getSimpleName();
        printStackTrace(logger, tag);
    }

    /**
     * Print a StackTrace from any given point of your source code.
     * @param tag Used to identify the source of a log message. It usually identifies the class or activity where the log call occurs.
     * @param logger A logger for output
     */
    public static void printStackTrace(Logger logger, String tag) {
        for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
            logger.d(tag, String.valueOf(ste));
        }
    }

}
