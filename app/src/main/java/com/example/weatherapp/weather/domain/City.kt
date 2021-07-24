package com.example.weatherapp.weather.domain

data class City(
    val weather: Weather,
    val temperature: Main,
    val time: Long = 0,
    val locationName: String = ""
)


data class Weather(
    val main: String = "",
    val description: String = "",
    val icon: String = "",
)

data class Main(
    val temperature: Int = 0,
    val feelsLike: Int = 0,
    val temperatureMin: Int = 0,
    val temperatureMax: Int = 0,
    val pressure: Int = 0,
    val humidity: Int = 0,
)
