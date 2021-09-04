package com.example.weathertest


import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.weathertest.api.RetrofitInstance
import com.example.weathertest.database.HourWeatherCondition
import com.example.weathertest.database.RealmConfig
import com.example.weathertest.database.WeatherCondition
import com.example.weathertest.databinding.ActivityMainBinding
import com.example.weathertest.pojo.DayOfWeek
import com.example.weathertest.pojo.HourInDay
import com.google.android.gms.location.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmList


class MainActivity : AppCompatActivity() {

    private var longitude: Double? = 0.0
    private var latitude: Double? = 0.0
    private lateinit var realm: Realm
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.actualWeatherContainer, ActualWeatherFragment())
            .replace(R.id.hourWeatherContainer,HourlyWeatherFragment())
            .commit()

        realm = Realm.getDefaultInstance()

        val lm = getSystemService(LOCATION_SERVICE) as LocationManager
        if (checkPermission()) {
            val location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            longitude = location?.longitude
            latitude = location?.latitude
            Log.i("NEW LOCATION", "${longitude},${latitude}")
        }
        getRetrofit(latitude, longitude)
     //   println(realm.where(WeatherCondition::class.java).findAll())
        println(realm.where(HourWeatherCondition::class.java).findAll())

    }

    private fun checkPermission(): Boolean {
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

    fun getRetrofit(lat: Double?, longt: Double?) {
        RetrofitInstance.instance.getWeather(lat, longt)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val i = it
                Log.i("IINFO", it.currently?.temperature.toString())
                Log.i("IINFO", it.latitude.toString())
                Log.i("IINFO", it.longitude.toString())
                Log.i("IINFO", it.timezone.toString())
                Log.i("IINFO", it.currently?.icon.toString())

                realm.beginTransaction()
                val weather = WeatherCondition().apply {
                    actualLocation = it.timezone
                    actualHumidity = it.currently?.humidity!!
                    actualPressure = it.currently.pressure
                    actualTemperature = it.currently.temperature
                    actualUVindex = it.currently.uvIndex
                    actualWind = it.currently.windSpeed
                }
                realm.deleteAll()
                realm.insert(weather)
                for (hour in it.hourly?.data!!) {
                    var weatherHour = HourWeatherCondition().apply {
                        hourTime = hour.time
                        hourTemperature = hour.temperature
                        icon = hour.icon
                    }
                    realm.insert(weatherHour)
                }

                realm.commitTransaction()

            }, {
                println(it.message.toString())
            }, {}
            )
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}


