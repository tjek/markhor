/*******************************************************************************
 * Copyright 2015 ShopGun
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.shopgun.android.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

public class PackageUtils {

    public static final String TAG = Tag.from(PackageUtils.class);

    private PackageUtils() {
        // private constructor
    }

    /**
     * Check if a given package is installed
     * @param ctx a context
     * @param packageName the package name to check state for
     * @return {@code true} if the package is installed, else {@code false}
     */
    public static boolean isInstalled(Context ctx, String packageName) {
        try {
            getPackageInfo(ctx, packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * Show this package in Google Play.
     * <p>Fallback mode to the old Market if needed.
     *
     * @param ctx a context
     * @throws ActivityNotFoundException &nbsp;
     */
    public static void openGooglePlay(Context ctx) throws ActivityNotFoundException {
        openGooglePlay(ctx, ctx.getPackageName());
    }

    /**
     * Show a package in Google Play.
     * <p>Fallback mode to the old Market if needed.
     *
     * @param ctx a context
     * @param packageName the package to show in Google Play
     * @throws ActivityNotFoundException &nbsp;
     */
    public static void openGooglePlay(Context ctx, String packageName) throws ActivityNotFoundException {
        try {
            startPlay(ctx, "market://details?id=" + packageName);
        } catch (ActivityNotFoundException exception) {
            // don't need a catch, as this is a URL, worst case it's being opened in a browser
            startPlay(ctx, "http://play.google.com/store/apps/details?id=" + packageName);
        }
    }

    public static void startPlay(Context ctx, String uri) throws ActivityNotFoundException {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);
    }

    /**
     * Get the version name contained in the AndroidManifest
     *
     * @param c A {@link Context} to get the the info from
     * @return A version name string, or {@code null}
     * @throws ContextPackageNameNotFoundException if a package with the given
     *             name cannot be found on the system.
     */
    public static String getVersionName(Context c) throws ContextPackageNameNotFoundException {
        return getPackageInfo(c).versionName;
    }

    /**
     * Get the version code contained in the AndroidManifest
     *
     * @param c A {@link Context} to get the the info from
     * @return A version name string, or {@code null}
     * @throws ContextPackageNameNotFoundException if a package with the given
     *             name cannot be found on the system.
     */
    public static int getVersionCode(Context c) throws ContextPackageNameNotFoundException {
        return getPackageInfo(c).versionCode;
    }

    /**
     * Retrieve overall information about an application package that is installed on the system.
     *
     * @param c a context
     * @return Returns a PackageInfo object containing information about the package.
     * @throws ContextPackageNameNotFoundException if a package with the given
     *             name cannot be found on the system.
     */
    public static PackageInfo getPackageInfo(Context c) throws ContextPackageNameNotFoundException {
        return getPackageInfo(c, 0);
    }

    /**
     * Retrieve overall information about an application package that is installed on the system.
     *
     * @param c a context
     * @param flags Additional option flags. Use any combination of
     *            {@link PackageManager#GET_ACTIVITIES}, {@link PackageManager#GET_GIDS},
     *            {@link PackageManager#GET_CONFIGURATIONS}, {@link PackageManager#GET_INSTRUMENTATION},
     *            {@link PackageManager#GET_PERMISSIONS}, {@link PackageManager#GET_PROVIDERS},
     *            {@link PackageManager#GET_RECEIVERS}, {@link PackageManager#GET_SERVICES},
     *            {@link PackageManager#GET_SIGNATURES}, {@link PackageManager#GET_UNINSTALLED_PACKAGES} to
     *            modify the data returned.
     * @return Returns a PackageInfo object containing information about the package.
     * @throws ContextPackageNameNotFoundException if a package with the given
     *             name cannot be found on the system.
     */
    public static PackageInfo getPackageInfo(Context c, int flags) throws ContextPackageNameNotFoundException {
        try {
            return getPackageInfo(c, c.getPackageName(), flags);
        } catch (PackageManager.NameNotFoundException e) {
            throw new ContextPackageNameNotFoundException(c, e);
        }
    }

    /**
     * Retrieve all of the information we know about a particular package/application.
     *
     * @param c a context
     * @return Returns ApplicationInfo object containing information about the package.
     * @throws ContextPackageNameNotFoundException if a package with the given
     *             name cannot be found on the system.
     */
    public static Bundle getMetaData(Context c) {
        return getApplicationInfo(c, PackageManager.GET_META_DATA).metaData;
    }

    /**
     * Retrieve all of the information we know about a particular package/application.
     *
     * @param c a context
     * @return Returns ApplicationInfo object containing information about the package.
     * @throws ContextPackageNameNotFoundException if a package with the given
     *             name cannot be found on the system.
     */
    public static ApplicationInfo getApplicationInfo(Context c) throws ContextPackageNameNotFoundException {
        try {
            return getApplicationInfo(c, c.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            throw new ContextPackageNameNotFoundException(c, e);
        }
    }

    /**
     * Retrieve all of the information we know about a particular package/application.
     *
     * @param c a context
     * @param flags Additional option flags. Use any combination of
     * {@link PackageManager#GET_META_DATA}, {@link PackageManager#GET_SHARED_LIBRARY_FILES},
     * {@link PackageManager#GET_UNINSTALLED_PACKAGES} to modify the data returned.
     * @return Returns ApplicationInfo object containing information about the package.
     * @throws ContextPackageNameNotFoundException if a package with the given
     *             name cannot be found on the system.
     */
    public static ApplicationInfo getApplicationInfo(Context c, int flags) throws ContextPackageNameNotFoundException {
        try {
            return getApplicationInfo(c, c.getPackageName(), flags);
        } catch (PackageManager.NameNotFoundException e) {
            throw new ContextPackageNameNotFoundException(c, e);
        }
    }

    /**
     * Retrieve overall information about an application package that is installed on the system.
     *
     * <p>Throws {@link android.content.pm.PackageManager.NameNotFoundException} if an application
     * with the given package name cannot be found on the system.
     *
     * <p>For more info see {@link PackageManager#getApplicationInfo(String, int)}</p>
     *
     * @param c a context
     * @param packageName The full name of the package, e.i. com.shopgun.android.shopgun.
     * @param flags Additional option flags. Use any combination of
     *            {@link PackageManager#GET_ACTIVITIES}, {@link PackageManager#GET_GIDS},
     *            {@link PackageManager#GET_CONFIGURATIONS}, {@link PackageManager#GET_INSTRUMENTATION},
     *            {@link PackageManager#GET_PERMISSIONS}, {@link PackageManager#GET_PROVIDERS},
     *            {@link PackageManager#GET_RECEIVERS}, {@link PackageManager#GET_SERVICES},
     *            {@link PackageManager#GET_SIGNATURES}, {@link PackageManager#GET_UNINSTALLED_PACKAGES} to
     *            modify the data returned.
     * @return Returns a PackageInfo object containing information about the package.
     * @throws android.content.pm.PackageManager.NameNotFoundException if a package with the given
     *             name cannot be found on the system.
     */
    public static PackageInfo getPackageInfo(Context c, String packageName, int flags) throws PackageManager.NameNotFoundException {
        return c.getPackageManager().getPackageInfo(packageName, flags);
    }

    /**
     * Retrieve all of the information we know about a particular package/application.
     *
     * <p>Throws {@link android.content.pm.PackageManager.NameNotFoundException} if an application
     * with the given package name cannot be found on the system.
     *
     * <p>For more info see {@link PackageManager#getApplicationInfo(String, int)}</p>
     *
     * @param c a context
     * @param packageName The full name of the package, e.i. com.shopgun.android.shopgun.
     * @param flags Additional option flags. Use any combination of
     * {@link PackageManager#GET_META_DATA}, {@link PackageManager#GET_SHARED_LIBRARY_FILES},
     * {@link PackageManager#GET_UNINSTALLED_PACKAGES} to modify the data returned.
     * @return Returns ApplicationInfo object containing information about the package.
     * @throws android.content.pm.PackageManager.NameNotFoundException if a package with the given
     *             name cannot be found on the system.
     */
    public static ApplicationInfo getApplicationInfo(Context c, String packageName, int flags) throws PackageManager.NameNotFoundException {
        return c.getPackageManager().getApplicationInfo(packageName, flags);
    }

    /**
     * In the absurd case, that a package is running on a Android system, without being installed
     * we can throw this exception. Yes, this class is a 'hack'.
     */
    public static class ContextPackageNameNotFoundException extends RuntimeException {

        public ContextPackageNameNotFoundException(Context c, PackageManager.NameNotFoundException e) {
            this(c.getPackageName(), e);
        }

        public ContextPackageNameNotFoundException(String packageName, PackageManager.NameNotFoundException e) {
            super("The context package name (" + packageName + ") couldn't be found, WTF.", e);
        }

    }
}
