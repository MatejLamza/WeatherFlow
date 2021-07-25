package com.example.weatherapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import com.example.weatherapp.common.state.NetworkStatus
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class NetworkStatusListener(context: Context) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


    val networkStatus = callbackFlow<NetworkStatus> {
        val networkStatusCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                offer(NetworkStatus.Connected)
                Log.d("bbb", "Network available")
            }

            override fun onUnavailable() {
                offer(NetworkStatus.NotConnected)
                Log.d("bbb", "Network unavilable")
            }

            override fun onLost(network: Network) {
                offer((NetworkStatus.NotConnected))
                Log.d("bbb", "Lost network")
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


}
