package com.example.weatherapp.weather.flow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.utils.extensions.toCelsiusString
import com.example.weatherapp.utils.extensions.toHoursString
import com.example.weatherapp.weather.domain.HourlyForecast
import kotlinx.android.synthetic.main.item_hourly_weather.view.*

class HourlyForecastAdapter :
    RecyclerView.Adapter<HourlyForecastAdapter.HourlyForecastViewHolder>() {

    var hourlyForecast: List<HourlyForecast> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyForecastViewHolder =
        HourlyForecastViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_hourly_weather, parent, false)
        )


    override fun onBindViewHolder(holder: HourlyForecastViewHolder, position: Int) {
        holder.hourlyForecast = hourlyForecast[position]
    }

    override fun getItemCount(): Int = hourlyForecast.size


    inner class HourlyForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var hourlyForecast: HourlyForecast? = null
            set(value) {
                field = value
                if (value != null) {
                    itemView.hour.text = value.dt.toHoursString()
                    Glide.with(itemView.context)
                        .load("https://openweathermap.org/img/wn/${value.icon}@2x.png")
                        .into(itemView.hourlyIcon)
                    itemView.hourlyTemp.text = value.temperature.toCelsiusString()
                }
            }
    }
}
