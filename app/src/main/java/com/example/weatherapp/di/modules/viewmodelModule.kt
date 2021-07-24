package com.example.weatherapp.di.modules

import com.example.weatherapp.weather.flow.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


val viewModelModule: Module = module {
    viewModel { WeatherViewModel(weatherRepo = get(), networkStatusListener = get()) }
}
