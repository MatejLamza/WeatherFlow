package com.example.weatherapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import com.example.weatherapp.common.state.NetworkStatus
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class NetworkStatusListener(context: Context) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


    val networkStatus = callbackFlow<NetworkStatus> {
        //This will ensure we have current network status of device immediately available instead of waiting for callback to invoke
        offer(if (isConnected()) NetworkStatus.Connected else NetworkStatus.NotConnected)
        val networkStatusCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                offer(NetworkStatus.Connected)
            }

            override fun onUnavailable() {
                offer(NetworkStatus.NotConnected)
            }

            override fun onLost(network: Network) {
                offer((NetworkStatus.NotConnected))
            }
        }
        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(request, networkStatusCallback)

        //This will be triggered when Channel is canceled or closed
        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkStatusCallback)
        }
    }

    private fun isConnected(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            return when (capabilities != null) {
                capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    true
                }
                capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    true
                }
                else -> false
            }
        } else {
            return connectivityManager.activeNetworkInfo?.isConnected ?: false
        }
    }


}
