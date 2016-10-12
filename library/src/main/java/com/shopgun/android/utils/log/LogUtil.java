package com.shopgun.android.utils.log;

public class LogUtil {

    public static final String TAG = LogUtil.class.getSimpleName();
    
    /**
     * Print the calling method by name and linenumber.
     * <p>com.shopgun.android.utils.MyClass.myMethod(MyClass.java:87)</p>
     * @param logger A logger for output
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
    public static void printMethod(int element) {
        LogUtil.printMethod(L.getLogger(), element);
    }

    /**
     * Print the calling method by name and linenumber.
     * <p>com.shopgun.android.utils.MyClass.myMethod(MyClass.java:87)</p>
     *
     * @param logger A logger for output
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
     * <p>This method will use the default logger found in {@link L#getLogger()}</p>
     */
    public static void printStackTrace() {
        LogUtil.printStackTrace(L.getLogger());
    }

    /**
     * Print a StackTrace from any given point of your source code.
     * Used to identify the source of a log message. It usually identifies the class or activity where the log call occurs.
     * @param logger A logger for output
     */
    public static void printStackTrace(Logger logger) {
        String tag = Thread.currentThread().getStackTrace()[3].getClass().getSimpleName();
        printStackTrace(logger, tag);
    }


    /**
     * Print a StackTrace from any given point of your source code.
     * <p>This method will use the default logger found in {@link L#getLogger()}</p>
     * @param tag Used to identify the source of a log message. It usually identifies the class or activity where the log call occurs.
     */
    public static void printStackTrace(String tag) {
        LogUtil.printStackTrace(L.getLogger(), tag);
    }

    /**
     * Print a StackTrace from any given point of your source code.
     * @param logger A logger for output
     * @param tag Used to identify the source of a log message. It usually identifies the class or activity where the log call occurs.
     */
    public static void printStackTrace(Logger logger, String tag) {
        for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
            logger.d(tag, String.valueOf(ste));
        }
    }

    /**
     * Print a StackTrace from any given point of your source code. But only include the lines
     * given as {@code first} and {@code last} in the arguments.
     * <p>This method will use the default logger found in {@link L#getLogger()}</p>
     * @param tag Used to identify the source of a log message. It usually identifies the class or activity where the log call occurs.
     * @param first first line to print
     * @param last last line to print
     */
    public static void printStackTrace(String tag, int first, int last) {
        LogUtil.printStackTrace(L.getLogger(), tag, first, last);
    }

    /**
     * Print a StackTrace from any given point of your source code. But only include the lines
     * given as {@code first} and {@code last} in the arguments.
     * @param logger A logger for output
     * @param tag Used to identify the source of a log message. It usually identifies the class or activity where the log call occurs.
     * @param first first line to print
     * @param last last line to print
     */
    public static void printStackTrace(Logger logger, String tag, int first, int last) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        first = Math.min(first, stackTraceElements.length);
        last = Math.min(last, stackTraceElements.length);
        for (int i = first; i < last; i++) {
            StackTraceElement ste = stackTraceElements[i];
            L.d(tag, String.valueOf(ste));
        }
    }

}
