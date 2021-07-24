package com.example.weatherapp.weather.flow

import androidx.lifecycle.*
import com.example.weatherapp.utils.NetworkStatusListener
import com.example.weatherapp.weather.domain.City
import com.example.weatherapp.weather.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherRepo: WeatherRepository,
    private val networkStatusListener: NetworkStatusListener
) : ViewModel() {

    private val _currentWeather = MutableLiveData<City>()
    val currentWeather: LiveData<City> = _currentWeather

    val networkStatus = networkStatusListener.networkStatus
        .asLiveData(Dispatchers.IO)


    fun getCurrentWeather(city: String, units: String = "metric") {
        viewModelScope.launch {
            weatherRepo.fetchCurrentWeather(city, units).collect { cityNetwork ->
                _currentWeather.value = cityNetwork
            }
        }
    }
}
