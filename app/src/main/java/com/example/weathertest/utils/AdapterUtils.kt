package com.example.weathertest.utils

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.example.weathertest.R


    fun setIcon(weather:String?, imageView: ImageView){
        when (weather) {
            "clear-day" -> imageView.setImageResource(R.drawable.clear_day)
            "clear-night" -> imageView.setImageResource(R.drawable.clear_night)
            "rain" -> imageView.setImageResource(R.drawable.rain)
            "snow" -> imageView.setImageResource(R.drawable.snow)
            "sleet" -> imageView.setImageResource(R.drawable.sleet)
            "wind" -> imageView.setImageResource(R.drawable.wind)
            "fog" -> imageView.setImageResource(R.drawable.fog)
            "cloudy" -> imageView.setImageResource(R.drawable.cloudy)
            "partly-cloudy-day" -> imageView.setImageResource(R.drawable.partly_cloudy_day)
            "partly-cloudy-night" -> imageView.setImageResource(R.drawable.partly_cloudy_night)
            else -> imageView.setImageResource(R.drawable.ic_circle)
        }
    }

    fun setWeather(weather:String?, textView: TextView, context: Context){
        when (weather) {
            "clear-day" -> textView.text = context.getString(R.string.clear_day)
            "clear-night" -> textView.text = context.getString(R.string.clear_night)
            "rain" -> textView.text =  context.getString(R.string.Rain)
            "snow" -> textView.text = context.getString(R.string.snow)
            "sleet" -> textView.text = context.getString(R.string.sleet)
            "wind" -> textView.text =  context.getString(R.string.Wind)
            "fog" -> textView.text = context.getString(R.string.fog)
            "cloudy" -> textView.text = context.getString(R.string.cloudy)
            "partly-cloudy-day" -> textView.text = context.getString(R.string.cloudy_day)
            "partly-cloudy-night" -> textView.text = context.getString(R.string.cloudly_night)
            else -> textView.text = context.getString(R.string.string_weather)
        }
    }
