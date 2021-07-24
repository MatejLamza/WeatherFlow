package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.di.WeatherAppDI
import com.example.weatherapp.utils.FlipperInitializer
import org.koin.android.ext.android.get

class WeatherApplication : Application() {
    private val weatherAppDI: WeatherAppDI by lazy { WeatherAppDI(this) }
    override fun onCreate() {
        super.onCreate()
        weatherAppDI.initialize()
        get<FlipperInitializer>().initialize()

    }
}
