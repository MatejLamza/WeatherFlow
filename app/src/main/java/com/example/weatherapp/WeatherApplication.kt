package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.di.WeatherAppDI

class WeatherApplication : Application() {
    private val weatherAppDI: WeatherAppDI by lazy { WeatherAppDI(this) }
    override fun onCreate() {
        super.onCreate()
        weatherAppDI.initialize()
    }
}
