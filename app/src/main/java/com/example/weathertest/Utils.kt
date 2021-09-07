package com.example.weathertest

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat


fun setIcon(weather:String?, imageView:ImageView){
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
fun setWeather(weather:String?, textView: TextView,context:Context){
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


 fun AppCompatActivity.requestPermission(PERMISSION_ID:Int) {
     ActivityCompat.requestPermissions(
         this,
         arrayOf(
             android.Manifest.permission.ACCESS_FINE_LOCATION,
             android.Manifest.permission.ACCESS_COARSE_LOCATION
         ),
         PERMISSION_ID
     )
 }

fun AppCompatActivity.checkPermission(): Boolean {
    if (ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
        ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        return true
    }

    return false
}












