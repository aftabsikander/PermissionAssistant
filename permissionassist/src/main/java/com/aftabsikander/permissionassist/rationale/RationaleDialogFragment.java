package com.aftabsikander.permissionassist.rationale;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;

import com.aftabsikander.permissionassist.interfaces.PermissionCallback;
import com.aftabsikander.permissionassist.interfaces.PermissionCallbacks;

/**
 * Created by afali on 3/30/2017.
 */

/**
 * {@link DialogFragment} to display rationale for permission requests when the request comes from
 * a Fragment or Activity that can host a Fragment.
 */
@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
public class RationaleDialogFragment extends DialogFragment {

    private PermissionCallbacks mPermissionCallbacks;
    private PermissionCallback mPermissionCallback;

    /****
     * {@link DialogFragment} to display rationale for permission requests when the request comes from
     * a Fragment or Activity that can host a Fragment.
     *
     * @param positiveButton custom text for positive button
     * @param negativeButton custom text for negative button
     * @param requestCode    request code to track this request, must be < 256.
     * @param rationaleMsg   a message explaining why the application needs this set of permissions,
     *                       will be displayed if the user rejects the request the first time.
     * @param permissions    a set of permissions to be requested.
     * @return {@link RationaleDialogFragment} instances which would be displayed on screen.
     */
    public static RationaleDialogFragment newInstance(
            @StringRes int positiveButton, @StringRes int negativeButton,
            @NonNull String rationaleMsg, int requestCode, @NonNull String[] permissions) {

        // Create new Fragment
        RationaleDialogFragment dialogFragment = new RationaleDialogFragment();

        // Initialize configuration as arguments
        RationaleDialogConfig config = new RationaleDialogConfig(
                positiveButton, negativeButton, rationaleMsg, requestCode, permissions);
        dialogFragment.setArguments(config.toBundle());

        return dialogFragment;
    }

    @SuppressLint("NewApi")
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // getParentFragment() requires API 17 or higher
        boolean isAtLeastJellyBeanMR1 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;

        if (isAtLeastJellyBeanMR1 && getParentFragment() != null) {
            if (getParentFragment() instanceof PermissionCallbacks) {
                mPermissionCallbacks = (PermissionCallbacks) getParentFragment();
            } else if (getParentFragment() instanceof PermissionCallback) {
                mPermissionCallback = (PermissionCallback) getParentFragment();
            }
        } else if (context instanceof PermissionCallbacks) {
            mPermissionCallbacks = (PermissionCallbacks) context;
        } else if (context instanceof PermissionCallback) {
            mPermissionCallback = (PermissionCallback) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPermissionCallbacks = null;
        mPermissionCallback = null;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Rationale dialog should not be cancelable
        setCancelable(false);

        // Get config from arguments, create click listener
        RationaleDialogConfig config = new RationaleDialogConfig(getArguments());
        RationaleDialogClickListener clickListener
                = new RationaleDialogClickListener(this, config, mPermissionCallbacks);

        if (mPermissionCallbacks == null && mPermissionCallback != null) {
            clickListener = new RationaleDialogClickListener(this, config, mPermissionCallbacks);
        } else if (mPermissionCallback != null) {
            clickListener = new RationaleDialogClickListener(this, config, mPermissionCallback);
        }

        // Create an AlertDialog
        return config.createDialog(getActivity(), clickListener);
    }

}
