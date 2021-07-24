package com.example.weatherapp.common.mvvm

import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.weather.flow.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


abstract class BaseActivity : AppCompatActivity(), View {

    protected val weatherViewModel: WeatherViewModel by viewModel()


    override fun showLoading() {
    }

    override fun showError(error: Throwable) {
    }

    override fun dismissLoading() {

    }
}
