package com.shopgun.android.utils.log;

public class LogUtil {

    public static final String TAG = LogUtil.class.getSimpleName();

    private static final int CALLING_ELEMENT = 4;

    /**
     * Print the calling method by name and linenumber.
     * <p>com.shopgun.android.utils.MyClass.myMethod(MyClass.java:87)</p>
     */
    public static void printMethod() {
        printMethod(L.getLogger(), CALLING_ELEMENT);
    }

    /**
     * Print the calling method by name and linenumber.
     * <p>com.shopgun.android.utils.MyClass.myMethod(MyClass.java:87)</p>
     * @param logger A logger for output
     */
    public static void printMethod(Logger logger) {
        printMethod(logger, CALLING_ELEMENT);
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
        String tag = getTagFromElement(ste);
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
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        printStackTrace(logger, getTagFromTrace(trace), trace, CALLING_ELEMENT, trace.length-1);
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
        printStackTrace(logger, tag, Thread.currentThread().getStackTrace());
    }

    private static void printStackTrace(Logger logger, String tag, StackTraceElement[] trace) {
        for (StackTraceElement ste : trace) {
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
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        printStackTrace(logger, tag, trace, first, last);
    }

    private static void printStackTrace(Logger logger, String tag, StackTraceElement[] trace, int first, int last) {
        first = Math.min(first, trace.length);
        last = Math.min(last, trace.length);
        for (int i = first; i < last; i++) {
            StackTraceElement ste = trace[i];
            logger.d(tag, String.valueOf(ste));
        }
    }

    /**
     * Returns a tag for use in a {@link Logger}, in the format of a class name.
     * E.g.: <p>MyClass</p>
     * @param trace A stack trace to get the info from
     * @return A tag
     */
    private static String getTagFromTrace(StackTraceElement[] trace) {
        return getTagFromElement(trace[CALLING_ELEMENT]);
    }

    /**
     * Returns a tag for use in a {@link Logger}, in the format of a class name.
     * E.g.: <p>MyClass</p>
     * @param element An element to get info from
     * @return A tag
     */
    private static String getTagFromElement(StackTraceElement element) {
        String[] split = element.getClassName().split("\\.");
        return split[split.length-1];
    }

}
