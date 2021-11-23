package com.example.runtimepermission

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var callBtn : Button
    lateinit var parentLayout : ConstraintLayout
    private val permissions = arrayOf(Manifest.permission.CALL_PHONE)
    companion object{
        const val PERMISSION_REQUEST_STORAGE = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callBtn  = findViewById(R.id.call_btn)
        parentLayout = findViewById(R.id.parent_layout)

        callBtn.setOnClickListener { downloadFile() }
    }


    private fun downloadFile() {
        // Check if the storage permission has been granted
        if (checkPermission(
                Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is already granted
            startDownloading()
        } else {
            //Requested permission.
            requestStoragePermission()
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_STORAGE) {
            // Request for camera permission.
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startDownloading()
            } else {
                // Permission request was denied.

                parentLayout.showSnackbar(R.string.storage_permission_denied, Snackbar.LENGTH_SHORT)
            }
        }
    }
    private fun requestStoragePermission() {

        // Permission has not been granted and must be requested.
        if (shouldRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
            // Provide an additional rationale to the user if the permission was not granted
            parentLayout.showSnackbar(
                R.string.storage_access_required,
                Snackbar.LENGTH_INDEFINITE, R.string.ok
            ) {
                requestAllPermissions(permissions, PERMISSION_REQUEST_STORAGE)
            }
        } else {
            // Request the permission with array.
            requestAllPermissions(permissions, PERMISSION_REQUEST_STORAGE)
        }
    }
    private fun startDownloading() {
        // do download stuff here
        parentLayout.showSnackbar(R.string.calling, Snackbar.LENGTH_LONG)
    }
}