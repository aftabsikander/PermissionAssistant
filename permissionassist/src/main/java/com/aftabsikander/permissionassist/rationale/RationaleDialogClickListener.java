package com.aftabsikander.permissionassist.rationale;

/**
 * Created by afali on 3/30/2017.
 */

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.aftabsikander.permissionassist.interfaces.PermissionCallback;
import com.aftabsikander.permissionassist.interfaces.PermissionCallbacks;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Click listener for either {@link RationaleDialogFragment} or {@link RationaleDialogFragmentCompat}.
 */
class RationaleDialogClickListener implements Dialog.OnClickListener {

    private Object mHost;
    private RationaleDialogConfig mConfig;
    private PermissionCallbacks mCallbacks;
    private PermissionCallback mCallback;

    /****
     * Creates a {@link RationaleDialogFragmentCompat} click listener which handles
     * {@link PermissionCallbacks} click events.
     *
     * @param compatDialogFragment display rationale for permission requests when the request
     * comes from a Fragment or Activity that can host a Fragment.
     * @param config  configuration for rationaleDialog
     * @param callbacks    provide {@link PermissionCallbacks} to requested context
     *
     */
    RationaleDialogClickListener(RationaleDialogFragmentCompat compatDialogFragment,
                                 RationaleDialogConfig config,
                                 PermissionCallbacks callbacks) {

        mHost = compatDialogFragment.getParentFragment() != null
                ? compatDialogFragment.getParentFragment()
                : compatDialogFragment.getActivity();

        mConfig = config;
        mCallbacks = callbacks;
    }

    /****
     * Creates a {@link RationaleDialogFragmentCompat} click listener which handles
     * {@link PermissionCallbacks} click events.
     *
     * @param compatDialogFragment display rationale for permission requests when the request
     * comes from a Fragment or Activity that can host a Fragment.
     * @param config  configuration for rationaleDialog
     * @param callback    provide {@link PermissionCallback} to requested context
     *
     */
    RationaleDialogClickListener(RationaleDialogFragmentCompat compatDialogFragment,
                                 RationaleDialogConfig config,
                                 PermissionCallback callback) {

        mHost = compatDialogFragment.getParentFragment() != null
                ? compatDialogFragment.getParentFragment()
                : compatDialogFragment.getActivity();

        mConfig = config;
        mCallback = callback;
    }

    /****
     * Creates a {@link RationaleDialogFragment} click listener which handles
     * {@link PermissionCallbacks} click events.
     *
     * @param dialogFragment display rationale for permission requests when the request
     * comes from a Fragment or Activity that can host a Fragment.
     * @param config  configuration for rationaleDialog
     * @param callbacks    provide {@link PermissionCallbacks} to requested context
     *
     */
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    RationaleDialogClickListener(RationaleDialogFragment dialogFragment,
                                 RationaleDialogConfig config,
                                 PermissionCallbacks callbacks) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mHost = dialogFragment.getParentFragment() != null
                    ? dialogFragment.getParentFragment()
                    : dialogFragment.getActivity();
        } else {
            mHost = dialogFragment.getActivity();
        }

        mConfig = config;
        mCallbacks = callbacks;
    }

    /****
     * Creates a {@link RationaleDialogFragment} click listener which handles
     * {@link PermissionCallback} click events.
     *
     * @param dialogFragment display rationale for permission requests when the request
     * comes from a Fragment or Activity that can host a Fragment.
     * @param config  configuration for rationaleDialog
     * @param callback    provide {@link PermissionCallback} to requested context
     *
     */
    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    RationaleDialogClickListener(RationaleDialogFragment dialogFragment,
                                 RationaleDialogConfig config,
                                 PermissionCallback callback) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mHost = dialogFragment.getParentFragment() != null
                    ? dialogFragment.getParentFragment()
                    : dialogFragment.getActivity();
        } else {
            mHost = dialogFragment.getActivity();
        }

        mConfig = config;
        mCallback = callback;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == Dialog.BUTTON_POSITIVE) {
            if (mHost instanceof Fragment) {
                ((Fragment) mHost).requestPermissions(mConfig.getPermissions(), mConfig.getRequestCode());
            } else if (mHost instanceof android.app.Fragment) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    ((android.app.Fragment) mHost)
                            .requestPermissions(mConfig.getPermissions(), mConfig.getRequestCode());
                } else {
                    throw new IllegalArgumentException(
                            "Target SDK needs to be greater than 23 if caller is android.app.Fragment");
                }
            } else if (mHost instanceof FragmentActivity) {
                ActivityCompat.requestPermissions(
                        (FragmentActivity) mHost, mConfig.getPermissions(), mConfig.getRequestCode());
            }
        } else {
            notifyPermissionDenied();
        }
    }

    /***
     *
     */
    private void notifyPermissionDenied() {
        if (mCallbacks != null) {
            mCallbacks.onPermissionsDenied(mConfig.getRequestCode(),
                    Arrays.asList(mConfig.getPermissions()));
        } else if (mCallback != null) {
            mCallback.onPermissionsResults(mConfig.getRequestCode(),
                    new ArrayList<String>(), Arrays.asList(mConfig.getPermissions()));
        }
    }
}
