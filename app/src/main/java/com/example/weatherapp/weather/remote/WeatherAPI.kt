package com.example.weatherapp.weather.remote

interface WeatherAPI {

    suspend fun fetchCurrentWeather(cityName: String)
}
