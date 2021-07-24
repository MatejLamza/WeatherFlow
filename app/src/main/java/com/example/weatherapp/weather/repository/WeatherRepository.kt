package com.example.weatherapp.weather.repository

import com.example.weatherapp.weather.remote.model.CityNetwork
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun fetchCurrentWeather(city: String, units: String = "metric"): Flow<CityNetwork>
}
