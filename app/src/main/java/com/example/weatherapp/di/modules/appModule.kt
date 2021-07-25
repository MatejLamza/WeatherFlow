package com.example.weatherapp.di.modules

import com.example.weatherapp.utils.ErrorParser
import com.example.weatherapp.utils.FlipperInitializer
import com.example.weatherapp.utils.LocationHelper
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single { FlipperInitializer(androidApplication()) }
    single { LocationHelper(context = get()) }
    single { ErrorParser(gson = get()) }

}
