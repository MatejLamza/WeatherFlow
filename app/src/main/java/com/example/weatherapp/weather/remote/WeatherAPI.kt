package com.example.weatherapp.weather.remote

import com.example.weatherapp.weather.remote.model.CityNetwork
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("weather")
    suspend fun fetchCurrentWeather(
        @Query("q") cityName: String,
        @Query("units") units: String = "metric"
    ): CityNetwork
}
