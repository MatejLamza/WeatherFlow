package com.example.weatherapp.di

import android.app.Application
import com.example.weatherapp.di.modules.appModule
import com.example.weatherapp.di.modules.networkModule
import com.example.weatherapp.di.modules.repositoryModule
import com.example.weatherapp.di.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module


class WeatherAppDI(private val application: Application) {

    private lateinit var koinApplication: KoinApplication
    private val modules: List<Module> = listOf(
        networkModule,
        repositoryModule,
        viewModelModule,
        appModule
    )

    fun initialize() {
        koinApplication = startKoin {
            androidContext(application)
            modules(modules)
        }
    }
}
