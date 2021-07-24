package com.example.weatherapp.weather.repository

import com.example.weatherapp.weather.remote.WeatherAPI
import com.example.weatherapp.weather.remote.model.CityNetwork
import kotlinx.coroutines.flow.flow

class WeatherRepositoryImpl(private val weatherAPI: WeatherAPI) : WeatherRepository {
    override suspend fun fetchCurrentWeather(city: String, units: String) = flow<CityNetwork> {
        emit(weatherAPI.fetchCurrentWeather(city, units))
    }

}
