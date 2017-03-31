package com.aftabsikander.permissionassist.interfaces;

import android.support.v4.app.ActivityCompat;

import java.util.List;

/**
 * Created by afali on 3/29/2017.
 */

/***
 * Provides Permission Callbacks to requested Actor
 */
public interface PermissionCallback extends ActivityCompat.OnRequestPermissionsResultCallback {

    /***
     * Single Callback which receives granted and denied Permission
     * @param requestCode request code to track this request.
     * @param grantedPerms a set of permissions which are granted.
     * @param deniedPerms a set of permissions which are rejected.
     */
    void onPermissionsResults(int requestCode, List<String> grantedPerms, List<String> deniedPerms);
}
