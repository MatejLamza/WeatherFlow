package com.example.weatherapp.splash

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.R
import com.example.weatherapp.common.mvvm.BaseActivity
import com.github.florent37.runtimepermission.kotlin.coroutines.experimental.askPermission

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        lifecycleScope.launchWhenCreated {
            kotlin.runCatching {
                askPermission(
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
                navigation.navigateToApp(this@SplashActivity)
            }.onFailure {
                Log.d("bbb", "Error se dogodio: ${it.message}")
                Toast.makeText(
                    baseContext,
                    "In order to get temperature for your current location, please the application location permission",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
