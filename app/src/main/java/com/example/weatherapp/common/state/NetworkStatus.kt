package com.example.weatherapp.common.state

sealed class NetworkStatus {
    object Connected : NetworkStatus()
    object NotConnected : NetworkStatus()
}
