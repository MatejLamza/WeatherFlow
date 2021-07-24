package com.example.weatherapp.common.mvvm

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.distinctUntilChanged
import com.example.weatherapp.common.NavigationViewModel
import com.example.weatherapp.common.state.NetworkStatus
import com.example.weatherapp.weather.flow.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


abstract class BaseActivity : AppCompatActivity(), View {

    protected val weatherViewModel: WeatherViewModel by viewModel()
    protected val navigation: NavigationViewModel by viewModel()


    protected fun trackInternetConnection() {
        weatherViewModel.networkStatus.distinctUntilChanged().observe(this) { status ->
            when (status) {
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

    override fun showLoading() {
    }

    override fun showError(error: Throwable) {
    }

    override fun dismissLoading() {

    }
}
