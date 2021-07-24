package com.example.weatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.utils.FlipperInitializer
import org.koin.android.ext.android.get

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        get<FlipperInitializer>().initialize()
    }
}
