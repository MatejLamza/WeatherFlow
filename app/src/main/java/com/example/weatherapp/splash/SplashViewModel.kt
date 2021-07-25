package com.example.weatherapp.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.common.state.State
import com.example.weatherapp.common.state.StateLiveData
import com.example.weatherapp.common.state.asLiveDataWithState
import com.example.weatherapp.utils.NetworkStatusListener

class SplashViewModel(
    private val networkStatusListener: NetworkStatusListener
) : ViewModel() {

    private val _state = StateLiveData()
    val state: LiveData<State> = _state

    val networkStatus = networkStatusListener.networkStatus.asLiveDataWithState(_state)

}
