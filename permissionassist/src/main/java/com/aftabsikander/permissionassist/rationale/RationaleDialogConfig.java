package com.aftabsikander.permissionassist.rationale;

/**
 * Created by afali on 3/30/2017.
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;


/**
 * Configuration for either {@link RationaleDialogFragment} or {@link RationaleDialogFragmentCompat}.
 */
public class RationaleDialogConfig {

    private static final String KEY_POSITIVE_BUTTON = "positiveButton";
    private static final String KEY_NEGATIVE_BUTTON = "negativeButton";
    private static final String KEY_RATIONALE_MESSAGE = "rationaleMsg";
    private static final String KEY_REQUEST_CODE = "requestCode";
    private static final String KEY_PERMISSIONS = "permissions";

    private int positiveButton;
    private int negativeButton;
    private int requestCode;
    private String rationaleMsg;
    private String[] permissions;

    /****
     * Creates configuration for displaying {@link RationaleDialogFragment} or
     * {@link RationaleDialogFragmentCompat}
     * @param positiveButton custom text for positive button
     * @param negativeButton custom text for negative button
     * @param rationaleMsg   a message explaining why the application needs this set of permissions,
     *                       will be displayed if the user rejects the request the first time.
     * @param requestCode request code to track this request, must be < 256.
     * @param permissions  a set of permissions to be requested.
     */
    RationaleDialogConfig(@StringRes int positiveButton, @StringRes int negativeButton,
                          @NonNull String rationaleMsg, int requestCode,
                          @NonNull String[] permissions) {

        this.positiveButton = positiveButton;
        this.negativeButton = negativeButton;
        this.rationaleMsg = rationaleMsg;
        this.requestCode = requestCode;
        this.permissions = permissions;
    }

    /***
     * Creates configuration for displaying {@link RationaleDialogFragment} or
     * {@link RationaleDialogFragmentCompat} using {@link Bundle}
     * @param bundle set of values which are used for configuration of dialog
     */
    public RationaleDialogConfig(Bundle bundle) {
        positiveButton = bundle.getInt(KEY_POSITIVE_BUTTON);
        negativeButton = bundle.getInt(KEY_NEGATIVE_BUTTON);
        rationaleMsg = bundle.getString(KEY_RATIONALE_MESSAGE);
        requestCode = bundle.getInt(KEY_REQUEST_CODE);
        permissions = bundle.getStringArray(KEY_PERMISSIONS);
    }

    /***
     * Creates configuration for displaying {@link RationaleDialogFragment} or
     * {@link RationaleDialogFragmentCompat}
     * @return bundle set of values which are used for configuration of dialog
     */
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_POSITIVE_BUTTON, positiveButton);
        bundle.putInt(KEY_NEGATIVE_BUTTON, negativeButton);
        bundle.putString(KEY_RATIONALE_MESSAGE, rationaleMsg);
        bundle.putInt(KEY_REQUEST_CODE, requestCode);
        bundle.putStringArray(KEY_PERMISSIONS, permissions);

        return bundle;
    }

    /***
     * Creates Alert dialog for  {@link RationaleDialogFragment} or
     * {@link RationaleDialogFragmentCompat}
     * @param  context the calling context
     * @param  listener click listener for {@link Dialog.android.content.DialogInterface.OnClickListener}
     * @return Build the {@link AlertDialog} from the specified options.
     */
    public AlertDialog createDialog(Context context, Dialog.OnClickListener listener) {
        return new AlertDialog.Builder(context)
                .setCancelable(false)
                .setPositiveButton(positiveButton, listener)
                .setNegativeButton(negativeButton, listener)
                .setMessage(rationaleMsg)
                .create();
    }

    public int getPositiveButton() {
        return positiveButton;
    }

    public void setPositiveButton(int positiveButton) {
        this.positiveButton = positiveButton;
    }

    public int getNegativeButton() {
        return negativeButton;
    }

    public void setNegativeButton(int negativeButton) {
        this.negativeButton = negativeButton;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public String getRationaleMsg() {
        return rationaleMsg;
    }

    public void setRationaleMsg(String rationaleMsg) {
        this.rationaleMsg = rationaleMsg;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }
}
