package com.example.runtimepermission

import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class AppCompatActivityExt: AppCompatActivity() {

}
    fun AppCompatActivity.checkPermission(permission: String) =
        ActivityCompat.checkSelfPermission(this, permission)
    fun AppCompatActivity.shouldRequestPermissionRationale(permission: String) =
        ActivityCompat.shouldShowRequestPermissionRationale(this, permission)
    fun AppCompatActivity.requestAllPermissions(
        permissionsArray: Array<String>,
        requestCode: Int
    ) {
        ActivityCompat.requestPermissions(this, permissionsArray, requestCode)

}