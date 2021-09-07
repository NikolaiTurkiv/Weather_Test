package com.example.weathertest.view.week_weather_fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weathertest.R
import com.example.weathertest.model.model.database.tables.WeekWeatherCondition
import com.example.weathertest.setIcon
import kotlinx.android.synthetic.main.day_of_week_card_view.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class WeekAdapter : RecyclerView.Adapter<WeekAdapter.WeekAdapterViewHolder>() {

    private var days:List<WeekWeatherCondition> = arrayListOf()

    fun refresDays(days: List<WeekWeatherCondition>) {
        this.days = days
        notifyDataSetChanged()
    }


    inner class WeekAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val timeDayOfWeek = itemView.textViewDayOfWeek
        val timeDate = itemView.textViewDate
        var icon = itemView.imageViewWeekTemp
        var minTemperature =itemView.textViewMinTemperature
        var maxTemperature = itemView.textViewMaxTemperature
        var wind = itemView.textViewWindSpeed
        var humidity = itemView.textViewHumidityPercent

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.day_of_week_card_view,
            parent,
            false
        )
        return WeekAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeekAdapterViewHolder, position: Int) {

        val day = days[position]

        with(holder){
            val sdfDayOfWeek = SimpleDateFormat("EEEE")
            val sdfNameMonth = SimpleDateFormat("dd MMMM")
            val dayOfWeek = Date(day.time.toLong() * 1000)
            val montDate = Date(day.time.toLong()*1000)

            timeDayOfWeek.text = sdfDayOfWeek.format(dayOfWeek)
            timeDate.text = sdfNameMonth.format(montDate)
            minTemperature.text = "${day.minTemperature.roundToInt()}\u00B0"
            maxTemperature.text = "${day.maxTemperature.roundToInt()}°"
            wind.text = day.wind.toString()
            humidity.text = day.humidity.toString()
            setIcon(day.icon,icon)
        }

    }

    override fun getItemCount(): Int {
        return days.size
    }
}