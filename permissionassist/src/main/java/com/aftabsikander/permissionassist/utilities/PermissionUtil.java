package com.aftabsikander.permissionassist.utilities;

import android.os.Build;
import android.util.Log;

/**
 * Created by afali on 3/21/2017.
 */

public final class PermissionUtil {

    private static final String TAG = "PermissionUtil";

    private PermissionUtil() {
        // Throw an exception if this ever *is* called
        throw new AssertionError("Instantiating utility class.");
    }


    /***
     * Utility method used to check is running device Android M
     * @return If device is Android M or above return true, otherwise return false
     */
    public static boolean isMDevice() {
        // Always return true for SDK < M, let the system deal with the permissions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return true;
        } else {
            Log.w(TAG, "isMDevice: API version < M, returning false");
            return false;
        }
    }


}
