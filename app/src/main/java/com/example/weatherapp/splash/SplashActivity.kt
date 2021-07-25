package com.example.weatherapp.splash

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.example.weatherapp.R
import com.example.weatherapp.common.mvvm.BaseActivity
import com.example.weatherapp.common.state.NetworkStatus
import com.example.weatherapp.common.state.observe
import com.github.florent37.runtimepermission.kotlin.coroutines.experimental.askPermission
import org.koin.android.ext.android.inject

class SplashActivity : BaseActivity() {

    private val splashViewModel: SplashViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        bind()
        lifecycleScope.launchWhenCreated {
            kotlin.runCatching {
                askPermission(
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            }.onFailure {
                Toast.makeText(
                    baseContext,
                    "In order to get temperature for your current location, please the application location permission",
                    Toast.LENGTH_LONG
                ).show()
            }
            navigation.navigateToApp(
                this@SplashActivity,
                splashViewModel.networkStatus.value == NetworkStatus.Connected
            )
        }
    }

    private fun bind() {
        splashViewModel.networkStatus.observe(this) {
        }
        splashViewModel.state.observe(this, this) {

        }
    }
}
