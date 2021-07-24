package com.example.weatherapp.weather.flow

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.example.weatherapp.R
import com.example.weatherapp.common.mvvm.BaseActivity
import com.example.weatherapp.common.state.NetworkStatus
import kotlinx.android.synthetic.main.activity_weather.*

class WeatherActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        setupUI()
        bind()
    }

    private fun setupUI() {
        searchLocation.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    weatherViewModel.getCurrentWeather(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun bind() {
        weatherViewModel.currentWeather.observe(this) { city ->
            locationName.text = city.locationName
            temperature.text = city.temperature.temperature.toString()
            description.text = city.weather.description
            maxMinTemperature.text =
                "${city.temperature.temperatureMax} / ${city.temperature.temperatureMin}"
            feelsLike.text = "Feels like: ${city.temperature.feelsLike}"
        }
        weatherViewModel.networkStatus.observe(this) {
            when (it) {
                is NetworkStatus.NotConnected -> Toast.makeText(
                    this,
                    "No internet connection",
                    Toast.LENGTH_LONG
                ).show()
                is NetworkStatus.Connected -> Toast.makeText(this, "Connected", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}
