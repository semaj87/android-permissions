package com.example.androidpermissions

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCameraPermission: Button = findViewById(R.id.btn_permissions)

        btnCameraPermission.setOnClickListener {
            // Checking the camera & GPS permission status against the PackageManager
            if (ContextCompat.checkSelfPermission(this@MainActivity, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this@MainActivity, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@MainActivity,
                    "You already have permission to use the camera.",
                    Toast.LENGTH_SHORT).show()
            }
            else {
                ActivityCompat.requestPermissions(this@MainActivity,
                    arrayOf(android.Manifest.permission.CAMERA,
                    android.Manifest.permission.ACCESS_FINE_LOCATION), CAMERA_AND_FINE_LOCATION_PERMISSION_CODE)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@MainActivity, "Permission granted for camera use.", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this@MainActivity,
                    "Oops, you were denied permission to use the camera. You can also grant this permission from settings.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val CAMERA_PERMISSION_CODE = 1
        private const val FINE_LOCATION_PERMISSION_CODE = 2
        private const val CAMERA_AND_FINE_LOCATION_PERMISSION_CODE = 12
    }
}