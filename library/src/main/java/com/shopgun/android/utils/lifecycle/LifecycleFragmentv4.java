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

package com.shopgun.android.utils.lifecycle;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class LifecycleFragmentv4 extends Fragment {

    public static final String TAG = LifecycleFragmentv4.class.getSimpleName();

    /**
     * Allows printLifecycleEvent() to print ALL life cycle events
     */
    private boolean mPrintLifecycle = true;

    /**
     * Allows printMenuEvent() to print ALL menu events
     */
    private boolean mPrintMenu = false;

    /**
     * Allows printOtherEvent() to print ALL other events (other than life cycle and menu)
     */
    private boolean mPrintOther = false;

	/*
	 * ########################################################################
	 *
	 *                           Miscellaneous
	 *
	 * ########################################################################
	 */

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        printMethod(mPrintOther);
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        printMethod(mPrintOther);
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onLowMemory() {
        printMethod(mPrintOther);
        super.onLowMemory();
    }

	/*
	 * ########################################################################
	 *
	 *                           Menu's
	 *
	 * ########################################################################
	 */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        printMethod(mPrintMenu);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        printMethod(mPrintMenu);
        super.onOptionsMenuClosed(menu);
    }

    @Override
    public void onDestroyOptionsMenu() {
        printMethod(mPrintMenu);
        super.onDestroyOptionsMenu();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        printMethod(mPrintMenu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        printMethod(mPrintMenu);
        return super.onContextItemSelected(item);
    }

	/*
	 * ########################################################################
	 *
	 *                           Lifecycle
	 *
	 * ########################################################################
	 */

    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        printMethod(mPrintLifecycle);
        super.onInflate(activity, attrs, savedInstanceState);
    }

    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        printMethod(mPrintLifecycle);
        super.onInflate(context, attrs, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        printMethod(mPrintLifecycle);
        super.onAttach(activity);
    }

    @Override
    public void onAttach(Context context) {
        printMethod(mPrintLifecycle);
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        printMethod(mPrintLifecycle);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        printMethod(mPrintLifecycle);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        printMethod(mPrintLifecycle);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        printMethod(mPrintLifecycle);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        printMethod(mPrintLifecycle);
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onStart() {
        printMethod(mPrintLifecycle);
        super.onStart();
    }

    @Override
    public void onResume() {
        printMethod(mPrintLifecycle);
        super.onResume();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        printMethod(mPrintLifecycle || mPrintMenu);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        printMethod(mPrintLifecycle || mPrintMenu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPause() {
        printMethod(mPrintLifecycle);
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        printMethod(mPrintLifecycle);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        printMethod(mPrintLifecycle);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        printMethod(mPrintLifecycle);
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        printMethod(mPrintLifecycle);
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        printMethod(mPrintLifecycle);
        super.onDestroy();
    }

    protected void printMethod(boolean print) {
        if (print) {
            StackTraceElement ste = Thread.currentThread().getStackTrace()[3];
//            String text = ste.getMethodName() + "(" + ste.getFileName() + ":" + ste.getLineNumber() + ")";
            Log.d(getClass().getSimpleName(), ste.getMethodName());
        }
    }

}
