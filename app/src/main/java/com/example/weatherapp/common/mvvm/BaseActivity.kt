package com.example.weatherapp.common.mvvm

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.distinctUntilChanged
import com.example.weatherapp.R
import com.example.weatherapp.common.NavigationViewModel
import com.example.weatherapp.common.state.NetworkStatus
import com.example.weatherapp.utils.ErrorParser
import com.example.weatherapp.weather.flow.WeatherViewModel
import kotlinx.android.synthetic.main.activity_weather.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


abstract class BaseActivity : AppCompatActivity(), View {

    protected val weatherViewModel: WeatherViewModel by viewModel()
    protected val navigation: NavigationViewModel by viewModel()
    protected val errorParser: ErrorParser by inject()

    //Optimization adjustment go with 2 if checks. If is online -> adjust all three of them else-> adjust opposite
    protected fun adjustContentVisiblity(isUserOnline: Boolean) {
        weatherContainer.visibility =
            if (!isUserOnline) android.view.View.GONE else android.view.View.VISIBLE
        myLocation.visibility =
            if (!isUserOnline) android.view.View.GONE else android.view.View.VISIBLE
        noInternetConnectionTitle.visibility =
            if (!isUserOnline) android.view.View.VISIBLE else android.view.View.GONE
        noInternetConnectionMessage.visibility =
            if (!isUserOnline) android.view.View.VISIBLE else android.view.View.GONE
    }


    protected fun trackInternetConnection() {
        weatherViewModel.networkStatus.distinctUntilChanged().observe(this) { status ->
            when (status) {
                is NetworkStatus.NotConnected -> Toast.makeText(
                    this,
                    getString(R.string.no_internet_connection),
                    Toast.LENGTH_LONG
                ).show()
                is NetworkStatus.Connected -> {
                    adjustContentVisiblity(true)
                }
            }
        }
    }

    override fun showLoading() {
        progress.visibility = android.view.View.VISIBLE
    }

    override fun showError(error: Throwable) {
        val message = errorParser.parse(error)
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun dismissLoading() {
        progress.visibility = android.view.View.GONE

    }
}
