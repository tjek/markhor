package com.shopgun.android.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {

    public static final String TAG = Tag.from(ExceptionUtils.class);

    private ExceptionUtils() {
        // private
    }

    /**
     * Takes an exception and returns it as a String.
     *
     * @param t An exception
     * @return The string representation of the exception
     */
    public static String exceptionToString(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    /**
     * Convert an {@link Throwable} to a {@link JSONObject}
     * @param t The throwable to add
     * @return A {@link JSONObject} representation of the given {@link Throwable}
     */
    public static JSONObject exceptionToJson(Throwable t) {

        JSONObject log = new JSONObject();
        try {
            log.put("exception", t.getClass().getName());
            log.put("stacktrace", exceptionToString(t));
            return log;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

}
