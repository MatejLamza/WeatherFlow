package com.example.weatherapp.weather.flow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.weather.remote.model.CityNetwork
import com.example.weatherapp.weather.repository.WeatherRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherRepo: WeatherRepository
) : ViewModel() {

    private val _currentWeather = MutableLiveData<CityNetwork>()
    val currentWeather: LiveData<CityNetwork> = _currentWeather


    fun getCurrentWeather(city: String, units: String = "metric") {
        viewModelScope.launch {
            weatherRepo.fetchCurrentWeather(city, units).collect { cityNetwork ->
                _currentWeather.value = cityNetwork
            }
        }
    }
}
