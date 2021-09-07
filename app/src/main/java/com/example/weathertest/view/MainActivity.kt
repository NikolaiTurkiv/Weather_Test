package com.example.weathertest.view


import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weathertest.R
import com.example.weathertest.checkPermission
import com.example.weathertest.model.model.DataManager
import com.example.weathertest.model.model.database.tables.HourWeatherCondition
import com.example.weathertest.requestPermission
import com.example.weathertest.view.actaul_weather_fragment.ActualWeatherFragment
import com.example.weathertest.view.hour_weather_fragment.HourlyWeatherFragment
import com.example.weathertest.view.week_weather_fragment.WeekWeatherFragment
import io.realm.Realm

class MainActivity : AppCompatActivity() {

    private lateinit var realm: Realm
    val PERMISSION_ID = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        realm = Realm.getDefaultInstance()

        if(this.checkPermission()){
            while (!this.checkPermission()){
                this.requestPermission(PERMISSION_ID)
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.actual_weather_container, ActualWeatherFragment())
            .replace(R.id.hour_weather_container, HourlyWeatherFragment())
            .replace(R.id.week_weather_container, WeekWeatherFragment())
            .commit()
    }

    override fun onStart() {
        super.onStart()
        val lm = getSystemService(LOCATION_SERVICE) as LocationManager
        if (this.checkPermission()) {
            val location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
           DataManager.getWeatherData(location?.latitude,location?.longitude)
        }
        println(realm.where(HourWeatherCondition::class.java).findAll())
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}



