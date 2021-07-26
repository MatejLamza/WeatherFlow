package com.example.weatherapp.utils.extensions

import com.example.weatherapp.weather.domain.HourlyForecast
import com.example.weatherapp.weather.remote.model.WeatherHourlyInfo


fun WeatherHourlyInfo.mapToDomain(): HourlyForecast {
    return HourlyForecast(
        this.dt,
        this.temperature.toInt(),
        this.weather[0].icon
    )
}
