package com.shopgun.android.utils.log;

public class LogUtil {

    /**
     * Print the calling method by name and linenumber.
     * <p>com.shopgun.android.utils.MyClass.myMethod(MyClass.java:87)</p>
     */
    public static void printCallingMethod(Logger logger) {
        printMethod(logger, 3);
    }

    /**
     * Print the calling method by name and linenumber.
     * <p>com.shopgun.android.utils.MyClass.myMethod(MyClass.java:87)</p>
     *
     * @param element argument to easily evaluate it
     */
    public static void printMethod(Logger logger, int element) {
        StackTraceElement ste = Thread.currentThread().getStackTrace()[element];
        String tag = ste.getClass().getSimpleName();
        String msg = ste.getMethodName() + "(" + ste.getFileName() + ":" + ste.getLineNumber() + ")";
        logger.d(tag, msg);
    }

    /**
     * Print a StackTrace from any given point of your source code.
     * @param tag     Used to identify the source of a log message. It usually identifies the class or activity where the log call occurs.
     */
    public static void printStackTrace(Logger logger, String tag) {
        for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
            logger.d(tag, String.valueOf(ste));
        }
    }

}
