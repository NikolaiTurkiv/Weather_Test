package com.example.weathertest.view.hour_weather_fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weathertest.R
import com.example.weathertest.model.model.database.tables.HourWeatherCondition
import com.example.weathertest.setIcon
import kotlinx.android.synthetic.main.hour_card_view.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class HourlyAdapter :
    RecyclerView.Adapter<HourlyAdapter.HourlyAdapterViewHolder>() {

    var hours: List<HourWeatherCondition> = arrayListOf()

    fun refresHours(hours: List<HourWeatherCondition>) {
        this.hours = hours
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
        val calendar = Calendar.getInstance()
        with(holder) {
            val sdfHour = SimpleDateFormat("HH:mm")
            val dayOfWeek = Date(hour.hourTime.toLong() * 1000)
            time.text = sdfHour.format(dayOfWeek)
            hourTemp.text = "${hour.hourTemperature.roundToInt()}°"
            setIcon(hour.icon,icon)
        }
    }

    override fun getItemCount(): Int {
        return hours.size
    }
}