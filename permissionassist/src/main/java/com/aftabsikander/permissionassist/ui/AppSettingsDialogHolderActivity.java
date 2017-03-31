package com.aftabsikander.permissionassist.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.RestrictTo;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by afali on 3/30/2017.
 */

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public class AppSettingsDialogHolderActivity extends AppCompatActivity
        implements DialogInterface.OnClickListener {

    /**
     * Check if the calling context has a set of permissions.
     *
     * @param context the calling context.
     * @param dialog  one ore more permissions, such as {@link Manifest.permission#CAMERA}.
     * @return Intent if all permissions are already granted, false if at least one permission is not
     * yet granted.
     */
    public static Intent createShowDialogIntent(Context context, AppSettingsDialog dialog) {
        return new Intent(context, AppSettingsDialogHolderActivity.class)
                .putExtra(AppSettingsDialog.EXTRA_APP_SETTINGS, dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppSettingsDialog dialog = getIntent().getParcelableExtra(AppSettingsDialog.EXTRA_APP_SETTINGS);
        dialog.setContext(this);
        dialog.setActivityOrFragment(this);
        dialog.setNegativeListener(this);
        dialog.showDialog();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setResult(resultCode, data);
        finish();
    }
}
