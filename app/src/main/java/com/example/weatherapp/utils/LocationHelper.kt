package com.example.weatherapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext


class LocationHelper(private val context: Context) {

    private var locationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    private val locationChannel = ConflatedBroadcastChannel<Location>()

    @SuppressLint("MissingPermission")
    private suspend fun setLocation() {
        withContext(IO) {
            locationClient.lastLocation.addOnSuccessListener {
                locationChannel.offer(it)
            }
        }
    }

    fun getLocation(): Flow<Location> {
        return locationChannel.asFlow()
            .onStart { setLocation() }
            .distinctUntilChanged()
    }
}
