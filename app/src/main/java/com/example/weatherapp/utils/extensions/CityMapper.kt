package com.example.weatherapp.utils.extensions

import com.example.weatherapp.weather.domain.City
import com.example.weatherapp.weather.domain.Cordinates
import com.example.weatherapp.weather.domain.Main
import com.example.weatherapp.weather.domain.Weather
import com.example.weatherapp.weather.remote.model.CityNetwork

fun CityNetwork.mapToDomain(): City {
    val currentWeather = this.weather[0]
    val currentMain = this.main
    return City(
        weather = Weather(
            main = currentWeather.main,
            description = currentWeather.description,
            icon = currentWeather.icon
        ),
        cordinates = Cordinates(
            latitude = this.cordinates.lat,
            longitude = this.cordinates.lon
        ),
        temperature = Main(
            temperature = currentMain.temperature.toInt(),
            feelsLike = currentMain.feelsLike.toInt(),
            temperatureMin = currentMain.temperatureMin.toInt(),
            temperatureMax = currentMain.temperatureMax.toInt(),
            pressure = currentMain.pressure,
            humidity = currentMain.humidity
        ),
        time = this.dt,
        locationName = this.name
    )
}
