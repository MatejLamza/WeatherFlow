package com.example.weatherapp.common

import android.content.Intent
import androidx.lifecycle.ViewModel
import com.example.weatherapp.common.state.launch
import com.example.weatherapp.splash.SplashActivity
import com.example.weatherapp.weather.flow.WeatherActivity

class NavigationViewModel : ViewModel() {

    fun navigateToApp(source: SplashActivity, isDeviceConnectedToInternet: Boolean) {
        launch {
            source.startActivity(
                Intent(source, WeatherActivity::class.java).putExtra(
                    WeatherActivity.IS_CONNECTED_TO_INTERNET,
                    isDeviceConnectedToInternet
                )
            )
            source.finishAffinity()
        }
    }
}
