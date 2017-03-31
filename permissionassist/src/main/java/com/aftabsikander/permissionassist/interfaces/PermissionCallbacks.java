/*
 * Copyright Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aftabsikander.permissionassist.interfaces;

import android.support.v4.app.ActivityCompat;

import java.util.List;

/**
 * Created by Aftab Ali on 3/21/2017.
 */

/***
 * Provides Permission Callbacks to requested Actor
 */
public interface PermissionCallbacks extends ActivityCompat.OnRequestPermissionsResultCallback {

    /***
     * Callback when all requested permission's are granted
     * @param requestCode request code to track this request.
     * @param permissionList a set of permissions to be requested.
     */
    void onPermissionsGranted(int requestCode, List<String> permissionList);

    /***
     * Callback when any requested permission's are denied
     * @param requestCode request code to track this request.
     * @param permissionList a set of permissions to be requested.
     */
    void onPermissionsDenied(int requestCode, List<String> permissionList);
}
