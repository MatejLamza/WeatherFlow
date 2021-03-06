package com.example.weatherapp.weather.remote.model

import com.google.gson.annotations.SerializedName

data class CityNetwork(
    @SerializedName("coord")
    val cordinates: Cordinates,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("base")
    val base: String = "",
    @SerializedName("main")
    val main: Main,
    @SerializedName("visiblity")
    val visibility: Int = 0,
    @SerializedName("wind")
    val wind: Wind,
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("dt")
    val dt: Long = 0,
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("timezone")
    val timezone: Long = 0,
    @SerializedName("id")
    val id: Long = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("cod")
    val cod: Int = 0
)

data class Cordinates(
    @SerializedName("lon")
    val lon: Double = 0.0,
    @SerializedName("lat")
    val lat: Double = 0.0,
)

data class Weather(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("main")
    val main: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("icon")
    val icon: String = "",

    )

data class Main(
    @SerializedName("temp")
    val temperature: Double = 0.0,
    @SerializedName("feels_like")
    val feelsLike: Double = 0.0,
    @SerializedName("temp_min")
    val temperatureMin: Double = 0.0,
    @SerializedName("temp_max")
    val temperatureMax: Double = 0.0,
    @SerializedName("pressure")
    val pressure: Int = 0,
    @SerializedName("humidity")
    val humidity: Int = 0,
)

data class Wind(
    @SerializedName("speed")
    val speed: Double = 0.0,
    @SerializedName("deg")
    val degrees: Double = 0.0,
    @SerializedName("gust")
    val gust: Double = 0.0
)

data class Clouds(
    @SerializedName("all")
    val all: Int
)

data class Sys(
    @SerializedName("type")
    val type: Int = 0,
    @SerializedName("id")
    val id: Long = 0,
    @SerializedName("country")
    val country: String = "",
    @SerializedName("sunrise")
    val sunrise: Long = 0,
    @SerializedName("sunset")
    val sunset: Long = 0
)
