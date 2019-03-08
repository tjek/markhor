package com.shopgun.android.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import androidx.core.content.ContextCompat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static android.os.Environment.MEDIA_MOUNTED;

public class FileUtils {

    public static final String TAG = FileUtils.class.getSimpleName();

    private FileUtils() {
        // private
    }

    public static File getExtDirectory(Context context, boolean preferExternal, String dir) {
        File cacheDir = null;
        if (preferExternal &&
                MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) &&
                ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {
            cacheDir = getExternalDir(context, dir);
        }

        if (cacheDir == null) {
            cacheDir = context.getCacheDir();
        }

        if (cacheDir == null) {
            String filesDir = context.getFilesDir().getPath();
            cacheDir = new File(filesDir + context.getPackageName() + "/" + dir + "/");
        }

        return cacheDir;
    }

    private static File getExternalDir(Context context, String dir) {
        File dataDir = new File(Environment.getExternalStorageDirectory(), "Android/data");
        File cacheDir = new File(new File(dataDir, context.getPackageName()), dir);
        if (!cacheDir.exists() && !cacheDir.mkdirs()) {
            return null;
        }
        return cacheDir;
    }

    public static boolean writeToFile(File file, String data, boolean append) {

        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;

        try {
            if (!file.getParentFile().exists() &&
                    !file.getParentFile().mkdirs()) {
                return false;
            }
            if (!file.exists() && !file.createNewFile()) {
                return false;
            }

            fos = new FileOutputStream(file, append);
            osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);
            bw.write(data);
            bw.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) fos.close();
                if (osw != null) osw.close();
                if (bw != null) bw.close();
            } catch (IOException e) {
                // ignore
            }
        }
        return false;
    }

}
