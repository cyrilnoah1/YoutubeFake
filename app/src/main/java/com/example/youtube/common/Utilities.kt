package com.example.youtube.common

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.view.View
import androidx.core.app.ActivityCompat

/**
 * Method to set the visibility of a desired [View] to [View.GONE].
 */
fun View.hide() {
    if (this.visibility != View.GONE) visibility = View.GONE
}

/**
 * Method to set the visibility of a desired [View] to [View.VISIBLE].
 */
fun View.show() {
    if (this.visibility != View.VISIBLE) visibility = View.VISIBLE
}

/**
 * Checks if the user has provided [Manifest.permission.CAMERA] permission.
 */
fun Context.checkCameraPermission(): Boolean = ActivityCompat.checkSelfPermission(
        this,
        Manifest.permission.CAMERA
) == PackageManager.PERMISSION_GRANTED

/**
 * Method to request [Manifest.permission.CAMERA] permission and return the
 * result to the [Activity]'s [Activity.onActivityResult].
 */
fun Activity.requestCameraPermission(requestCode: Int) {

    if (this.checkCameraPermission()) return

    ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            requestCode
    )
}