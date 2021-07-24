package com.example.weatherapp.di.modules

import com.example.weatherapp.weather.repository.WeatherRepository
import com.example.weatherapp.weather.repository.WeatherRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule: Module = module {
    single<WeatherRepository> {
        WeatherRepositoryImpl(weatherAPI = get())
    }
}
