package com.example.weatherapp.weather.repository

import com.example.weatherapp.weather.domain.City
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun fetchCurrentWeather(city: String, units: String = "metric"): Flow<City>
    suspend fun fetchCurrentWeatherForCordinates(
        longitude: Double,
        latitude: Double,
        units: String = "metric"
    ): Flow<City>
}
