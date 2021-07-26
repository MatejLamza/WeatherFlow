package com.example.weatherapp.weather.domain

class HourlyForecastUnavailable : Exception() {
    override val message: String?
        get() = "Hourly forecast currently unavailable"
}
