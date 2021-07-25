package com.example.weatherapp.weather.flow

import android.util.Log
import androidx.lifecycle.*
import com.example.weatherapp.common.state.launch
import com.example.weatherapp.utils.LocationHelper
import com.example.weatherapp.utils.NetworkStatusListener
import com.example.weatherapp.weather.domain.City
import com.example.weatherapp.weather.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherRepo: WeatherRepository,
    private val networkStatusListener: NetworkStatusListener,
    locationHelper: LocationHelper
) : ViewModel() {

    private val _currentWeather = MutableLiveData<City>()
    val currentWeather: LiveData<City> = _currentWeather

    val networkStatus = networkStatusListener.networkStatus
        .asLiveData(Dispatchers.IO)

    val location = locationHelper.getLocation().asLiveData(Dispatchers.IO)

    fun getCurrentWeather(city: String, units: String = "metric") {
        viewModelScope.launch {
            weatherRepo.fetchCurrentWeather(city, units).collect { cityNetwork ->
                _currentWeather.value = cityNetwork
            }
        }
    }

    //TODO Check if user has location enabled
    fun getCurrentWeatherForCordinates(latitude: Double, longitude: Double) {
        launch {
            weatherRepo.fetchCurrentWeatherForCordinates(longitude, latitude).collect {
                Log.d("bbb", "Dobio sam city: ${it.temperature.temperature}")
            }
        }
    }


}
