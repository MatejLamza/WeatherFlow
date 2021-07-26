package com.example.weatherapp.weather.flow

import android.Manifest
import android.content.Context
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.common.mvvm.BaseActivity
import com.example.weatherapp.common.state.observe
import com.example.weatherapp.utils.extensions.toCelsiusString
import com.example.weatherapp.weather.domain.PermissionException
import com.github.florent37.runtimepermission.kotlin.coroutines.experimental.askPermission
import kotlinx.android.synthetic.main.activity_weather.*
import retrofit2.HttpException

class WeatherActivity : BaseActivity() {

    companion object {
        const val IS_CONNECTED_TO_INTERNET = "is-device-connected-to-internet"
    }

    private val isDeviceConnectedToInternet: Boolean by lazy {
        intent.getBooleanExtra(IS_CONNECTED_TO_INTERNET, false)
    }
    private val hourlyForecastAdapter: HourlyForecastAdapter by lazy {
        HourlyForecastAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        trackInternetConnection()
        adjustContentVisiblity(isDeviceConnectedToInternet)
        setupUI()
        bind()
    }

    private fun setupUI() {
        initRecyclerView()
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
        myLocation.setOnClickListener {
            val locationManager =
                this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val isLocationEnabled = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                locationManager.isLocationEnabled
            } else {
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            }
            if (isLocationEnabled) {
                weatherViewModel.refreshLocation()
            } else {
                Toast.makeText(this, getString(R.string.turn_on_your_location), Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun bind() {
        weatherViewModel.updateCurrentLocation.observe(this) {}
        weatherViewModel.currentWeather.observe(this) { city ->
            locationName.text = city.locationName
            temperature.text = city.temperature.temperature.toCelsiusString()
            description.text = city.weather.description
            maxMinTemperature.text =
                "${city.temperature.temperatureMax.toCelsiusString()} / ${city.temperature.temperatureMin.toCelsiusString()}"
            feelsLike.text =
                getString(
                    R.string.temperature_feels_like,
                    city.temperature.feelsLike.toCelsiusString()
                )
            Glide.with(this)
                .load("https://openweathermap.org/img/wn/${city.weather.icon}@2x.png")
                .into(weatherIcon)
        }
        weatherViewModel.state.observe(this, this, onError = {
            when (it) {
                is PermissionException -> {
                    lifecycleScope.launchWhenCreated {
                        kotlin.runCatching {
                            askPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                        }
                    }
                }
                is HttpException -> {
                    showError(it)
                }
                else -> {
                    Toast.makeText(
                        this,
                        getString(R.string.turn_on_internet),
                        Toast.LENGTH_LONG
                    ).show()
                    adjustContentVisiblity(false)
                }
            }
        }) {}
        weatherViewModel.hourlyForecast.observe(this) {
            hourlyForecastAdapter.hourlyForecast = it
        }
    }

    private fun initRecyclerView() {
        hourlyForecastList.adapter = hourlyForecastAdapter
    }
}
