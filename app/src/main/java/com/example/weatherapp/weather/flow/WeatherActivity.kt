package com.example.weatherapp.weather.flow

import android.os.Bundle
import android.widget.TextView
import com.example.weatherapp.R
import com.example.weatherapp.common.mvvm.BaseActivity

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
    }
}
