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

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.fragment.app.Fragment;

public class KeyboardUtils {

    public static final String TAG = Tag.from(KeyboardUtils.class);

    private KeyboardUtils() {
        // private constructor
    }

    /**
     * Request to show the soft input window from the context of the window that is currently accepting input.
     * @param dialog The current dialog
     */
    public static void show(Dialog dialog) {
        if (dialog != null) {
            show(dialog.getCurrentFocus());
        }
    }

    /**
     * Request to show the soft input window from the context of the window that is currently accepting input.
     * @param fragment The current fragment
     */
    public static void show(Fragment fragment) {
        if (fragment != null) {
            show(fragment.getActivity());
        }
    }

    /**
     * Request to show the soft input window from the context of the window that is currently accepting input.
     * @param fragment The current fragment
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void show(android.app.Fragment fragment) {
        if (fragment != null) {
            show(fragment.getActivity());
        }
    }

    /**
     * Request to show the soft input window from the context of the window that is currently accepting input.
     * @param activity The current activity
     */
    public static void show(Activity activity) {
        if (activity != null) {
            show(activity.getCurrentFocus());
        }
    }

    /**
     * Request to show the soft input window from the context of the window that is currently accepting input.
     * @param view The currently focused view
     */
    public static void show(View view) {
        setKeyboardVisible(view, false);
    }

    /**
     * Request to hide the soft input window from the context of the window that is currently accepting input.
     * @param dialog The current dialog
     */
    public static void hide(Dialog dialog) {
        if (dialog != null) {
            hide(dialog.getCurrentFocus());
        }
    }

    /**
     * Request to hide the soft input window from the context of the window that is currently accepting input.
     * @param fragment The current fragment
     */
    public static void hide(Fragment fragment) {
        if (fragment != null) {
            hide(fragment.getActivity());
        }
    }

    /**
     * Request to hide the soft input window from the context of the window that is currently accepting input.
     * @param fragment The current fragment
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void hide(android.app.Fragment fragment) {
        if (fragment != null) {
            hide(fragment.getActivity());
        }
    }

    /**
     * Request to hide the soft input window from the context of the window that is currently accepting input.
     * @param activity The current activity
     */
    public static void hide(Activity activity) {
        if (activity != null) {
            hide(activity.getCurrentFocus());
        }
    }

    /**
     * Request to hide the soft input window from the context of the window that is currently accepting input.
     * @param view The currently focused view
     */
    public static void hide(View view) {
        setKeyboardVisible(view, true);
    }

    /**
     * Request to show/hide the soft input window from the context of the window that is currently accepting input.
     *
     * @param view    A view to get a window token from
     * @param visible True to show input window, false to hide
     */
    public static void setKeyboardVisible(View view, boolean visible) {
        if (view != null) {
            setKeyboardVisible(view.getContext(), view, visible);
        }
    }

    /**
     * Request to show/hide the soft input window from the context of the window that is currently accepting input.
     *
     * @param context A context
     * @param view    A view to get a window token from
     * @param visible True to show input window, false to hide
     */
    public static void setKeyboardVisible(Context context, View view, boolean visible) {
        if (view != null) {
            setKeyboardVisible(context, view.getWindowToken(), visible);
        }
    }

    /**
     * Request to show/hide the soft input window from the context of the window that is currently accepting input.
     *
     * @param context     A context
     * @param windowToken A window token (IBinder)
     * @param visible     True to show input window, false to hide
     */
    public static void setKeyboardVisible(Context context, IBinder windowToken, boolean visible) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (visible) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        } else if (windowToken != null) {
            imm.hideSoftInputFromWindow(windowToken, 0);
        }
    }

}
