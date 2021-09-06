package com.example.weathertest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weathertest.database.HourWeatherCondition
import kotlinx.android.synthetic.main.hour_card_view.view.*

class HourlyAdapter(hours: List<HourWeatherCondition>) :
    RecyclerView.Adapter<HourlyAdapter.HourlyAdapterViewHolder>() {

    var hours: List<HourWeatherCondition> = hours
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class HourlyAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val time = itemView.textViewTime
        val hourTemp = itemView.textViewHourTemp
        val icon = itemView.imageViewHourWeather
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyAdapterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.hour_card_view, parent, false)
        return HourlyAdapterViewHolder(view)

    }

    override fun onBindViewHolder(holder: HourlyAdapterViewHolder, position: Int) {
        val hour = hours[position]
        with(holder) {
            time.text = hour.hourTime.toString()
            hourTemp.text = hour.hourTemperature.toString()
            when (hour.icon) {
                "clear-day" -> icon.setImageResource(R.drawable.clear_day)
                "clear-night" -> icon.setImageResource(R.drawable.clear_night)
                "rain" -> icon.setImageResource(R.drawable.rain)
                "snow" -> icon.setImageResource(R.drawable.snow)
                "sleet" -> icon.setImageResource(R.drawable.sleet)
                "wind" -> icon.setImageResource(R.drawable.wind)
                "fog" -> icon.setImageResource(R.drawable.fog)
                "cloudy" -> icon.setImageResource(R.drawable.cloudy)
                "partly-cloudy-day" -> icon.setImageResource(R.drawable.partly_cloudy_day)
                "partly-cloudy-night" -> icon.setImageResource(R.drawable.partly_cloudy_night)
                else -> icon.setImageResource(R.drawable.ic_circle)
            }
        }
    }

    override fun getItemCount(): Int {
        return hours.size
    }
}