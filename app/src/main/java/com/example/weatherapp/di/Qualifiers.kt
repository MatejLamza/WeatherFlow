package com.example.weatherapp.di

import org.koin.core.qualifier.named

object Qualifiers {
    val retrofitWeatherApp by lazy { named("retrofitWeatherApp") }
    val okHttpWeatherApp by lazy { named("okHttpWeatherApp") }
    val apiFullUrl by lazy { named("apiFullUrl") }

}
