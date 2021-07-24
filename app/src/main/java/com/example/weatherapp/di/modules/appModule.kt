package com.example.weatherapp.di.modules

import com.example.weatherapp.utils.FlipperInitializer
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single { FlipperInitializer(androidApplication()) }

}
