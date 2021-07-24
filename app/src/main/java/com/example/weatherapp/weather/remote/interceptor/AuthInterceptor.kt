package com.example.weatherapp.weather.remote.interceptor

import com.example.weatherapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader("appid", BuildConfig.APP_ID)
                .build()
        )
    }
}
