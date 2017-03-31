package com.aftabsikander.permissionassistant.sample;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.aftabsikander.permissionassist.PermissionAssistant;
import com.aftabsikander.permissionassist.interfaces.PermissionCallbacks;

import java.util.List;

/**
 * Created by afali on 3/30/2017.
 */

/**
 * NOTE: This class is used only for testing EasyPermissions outside of the support library.
 * <p>
 * See {@link MainActivity} for an example of how to use EasyPermissions.
 */

public class BasicActivity extends Activity implements PermissionCallbacks {

    private static final String TAG = "BasicActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        findViewById(R.id.button_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Request SMS permission
                PermissionAssistant.requestPermissions(BasicActivity.this, "NEED SMS PLZ", 1001,
                        Manifest.permission.READ_SMS);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // EasyPermissions handles the request result.
        PermissionAssistant.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.d(TAG, "onPermissionsGranted:" + requestCode + ":" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "onPermissionsDenied:" + requestCode + ":" + perms.size());
    }
}


