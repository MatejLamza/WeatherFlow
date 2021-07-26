package com.example.weatherapp.weather.domain


data class HourlyForecast(
    val dt: Long = 0,
    val temperature: Int = 0,
    val icon: String = "",
)
