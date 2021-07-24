package com.example.weatherapp.weather.flow

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.weatherapp.R
import com.example.weatherapp.common.mvvm.BaseActivity
import com.example.weatherapp.common.state.NetworkStatus

class WeatherActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        bind()
        weatherViewModel.getCurrentWeather("Zagreb")
    }

    private fun bind() {
        weatherViewModel.currentWeather.observe(this) { city ->
            findViewById<TextView>(R.id.testMessage).text = city.main.temperature.toString()
        }
        weatherViewModel.networkStatus.observe(this) {
            when (it) {
                is NetworkStatus.NotConnected -> Toast.makeText(
                    this,
                    "Nema neta",
                    Toast.LENGTH_LONG
                ).show()
                is NetworkStatus.Connected -> Toast.makeText(this, "Connected", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}
