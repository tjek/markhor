package com.shopgun.android.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import java.io.File;
import java.util.Map;
import java.util.Set;

public class SharedPreferencesUtils {

    private SharedPreferencesUtils() {
        // private
    }

    public static String getDefaultSharedPreferencesName(Context context) {
        return context.getPackageName() + "_preferences";
    }

    public static void copySharedPreferences(SharedPreferences fromPreferences, SharedPreferences toPreferences) {
        copySharedPreferences(fromPreferences, toPreferences, true);
    }

    @SuppressLint("CommitPrefEdits")
    public static void copySharedPreferences(SharedPreferences fromPreferences, SharedPreferences toPreferences, boolean clear) {

        SharedPreferences.Editor editor = toPreferences.edit();
        if (clear) {
            editor.clear();
        }
        copySharedPreferences(fromPreferences, editor);
        editor.commit();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @SuppressWarnings({"unchecked", "ConstantConditions"})
    public static void copySharedPreferences(SharedPreferences fromPreferences, SharedPreferences.Editor toEditor) {

        for (Map.Entry<String, ?> entry : fromPreferences.getAll().entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            if (value instanceof String) {
                toEditor.putString(key, ((String) value));
            } else if (value instanceof Set) {
                toEditor.putStringSet(key, (Set<String>) value); // EditorImpl.putStringSet already creates a copy of the set
            } else if (value instanceof Integer) {
                toEditor.putInt(key, (Integer) value);
            } else if (value instanceof Long) {
                toEditor.putLong(key, (Long) value);
            } else if (value instanceof Float) {
                toEditor.putFloat(key, (Float) value);
            } else if (value instanceof Boolean) {
                toEditor.putBoolean(key, (Boolean) value);
            }
        }
    }

    /**
     * Clear (delete) all SharedPreferences belonging to hte given Context.
     * @param ctx A {@link Context}
     */
    public static void clearSharedPreferences(Context ctx){
        File dir = new File(ctx.getFilesDir().getParent() + "/shared_prefs/");
        String[] children = dir.list();
        for (int i = 0; i < children.length; i++) {
            // clear each of the prefrances
            ctx.getSharedPreferences(children[i].replace(".xml", ""), Context.MODE_PRIVATE).edit().clear().commit();
        }
        // Make sure it has enough time to save all the commited changes
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // ignore
        }
        for (int i = 0; i < children.length; i++) {
            // delete the files
            new File(dir, children[i]).delete();
        }
    }

    /**
     * Move a SharedPreferences file to a new file (rename).
     *
     * <p>If the toPreferences file already exists, we will copy all keys, not override the file</p>
     *
     * Names should be the same as you use to instantiate your SharedPreferences Object.
     * e.g.: ctx.getSharedPreferences("yourPreferenceName", Context.MODE_PRIVATE).
     * If you are using PreferenceManager.getDefaultSharedPreferences(Context), then
     * just append "_preferences" to your name, and it's all good :-)
     *
     * @param ctx A {@link Context}
     * @param fromPreferencesName Name of old SharedPreferences
     * @param toPreferencesName Name of new SharedPreferences
     * @return {@code true} if success, else {@code false}
     */
    @SuppressLint("CommitPrefEdits")
    @SuppressWarnings({"unchecked", "ConstantConditions"})
    public static boolean moveSharedPreferences(Context ctx, String fromPreferencesName, String toPreferencesName){
        if (fromPreferencesName == null || toPreferencesName == null) {
            throw new IllegalArgumentException("Filenames cannot be null");
        }

        // make string name absolute
        String fromFileName = fromPreferencesName + ".xml";
        String toFileName = toPreferencesName + ".xml";

        // Check if old file exists
        String prefsPath = ctx.getFilesDir().getParent() + "/shared_prefs/";
        File fromPrefsFile = new File(prefsPath + fromFileName);
        if (fromPrefsFile.exists()) {
            // Write all pending transactions to file before moving
            SharedPreferences fromPrefs = ctx.getSharedPreferences(fromPreferencesName, Context.MODE_PRIVATE);
            fromPrefs.edit().commit();
            // Check is the new sharedprefs file exists
            File toPrefsFile = new File(prefsPath + toFileName);
            if (toPrefsFile.exists()) {
                SharedPreferences toPrefs = ctx.getSharedPreferences(toPreferencesName, Context.MODE_PRIVATE);
                copySharedPreferences(fromPrefs, toPrefs, false);
                return fromPrefsFile.delete();
            } else {
                return fromPrefsFile.renameTo(toPrefsFile);
            }
        }
        return false;
    }

}
