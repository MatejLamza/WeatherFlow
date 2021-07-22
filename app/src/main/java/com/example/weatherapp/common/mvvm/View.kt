package com.example.weatherapp.common.mvvm

interface View {
    fun showLoading()
    fun dismissLoading()
    fun showError(error: Throwable)
}
