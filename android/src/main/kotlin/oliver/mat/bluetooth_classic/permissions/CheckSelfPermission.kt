package oliver.mat.bluetooth_classic.permissions

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class CheckSelfPermission(private var activity: Activity) {

    @RequiresApi(Build.VERSION_CODES.S)
    private fun checkSelfAndroid12(activity: Activity) = ContextCompat.checkSelfPermission(activity, Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(activity, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun checkSelf(activity: Activity) = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED


    fun isPermissionsGranted(): Boolean {

        var permission = false

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.R) {
            if (checkSelf(activity)) {
                permission = true
            }
        } else {
            if (checkSelfAndroid12(activity)) {
                permission = true
            }
        }

        return permission
    }

    fun requirePermissions() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.R) {
            if (!checkSelf(activity)) {
                ActivityCompat.requestPermissions(activity,
                    PERMISSIONS, PERMISSION_REQUEST
                )
            }
        } else {
            if (!checkSelfAndroid12(activity)) {
                ActivityCompat.requestPermissions(activity,
                    PERMISSIONS_ANDROID_12, PERMISSION_REQUEST
                )
            }
        }
    }

    companion object {
        private const val PERMISSION_REQUEST = 2

        @RequiresApi(Build.VERSION_CODES.S)
        private val PERMISSIONS_ANDROID_12 = arrayOf(
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION)

        private val PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION)
    }
}