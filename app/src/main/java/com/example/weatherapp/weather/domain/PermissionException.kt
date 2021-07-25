package com.example.weatherapp.weather.domain

class PermissionException : Exception() {
    override val message: String?
        get() = "No permission granted"

}
