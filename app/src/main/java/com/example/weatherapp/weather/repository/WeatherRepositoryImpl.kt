package com.example.weatherapp.weather.repository

import com.example.weatherapp.utils.extensions.mapToDomain
import com.example.weatherapp.weather.domain.City
import com.example.weatherapp.weather.remote.WeatherAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepositoryImpl(private val weatherAPI: WeatherAPI) : WeatherRepository {
    override suspend fun fetchCurrentWeather(city: String, units: String) = flow<City> {
        emit(weatherAPI.fetchCurrentWeather(city, units).mapToDomain())
    }

    override suspend fun fetchCurrentWeatherForCordinates(
        longitude: Double,
        latitude: Double,
        units: String
    ): Flow<City> = flow {
        emit(weatherAPI.fetchCurrentWeatherForCordinates(latitude, longitude, units).mapToDomain())
    }

}
