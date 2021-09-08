package com.example.weathertest.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weathertest.*
import com.example.weathertest.model.model.DataManager
import com.example.weathertest.model.model.database.tables.HourWeatherCondition
import com.example.weathertest.utils.Constants
import com.example.weathertest.view.main_screen_fragment.MainScreenWeatherFragment
import com.example.weathertest.view.progressbar_fragment.ProgressBarFragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationServices
import io.realm.Realm

class MainActivity : AppCompatActivity() {

    private lateinit var realm: Realm
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        realm = Realm.getDefaultInstance()
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        if (!this.checkPermission()) {
                this.requestPermission(Constants.PERMITION_ID)

            getNewLocation(fusedLocationProviderClient, object : LocationCallback() {})

            supportFragmentManager.beginTransaction()
                .replace(R.id.main_activity_place_holder, ProgressBarFragment()).commit()
        }
    }

    override fun onStart() {
        super.onStart()
        if (checkPermission()) {
            fusedLocationProviderClient.lastLocation.addOnCompleteListener {
                val location = it.result
                if (location == null) {
                    getNewLocation(fusedLocationProviderClient, object : LocationCallback() {})
                } else {
                    DataManager.getWeatherData(location.latitude, location.longitude)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_activity_place_holder, MainScreenWeatherFragment())
                        .commit()
                }
            }
        }
        println(realm.where(HourWeatherCondition::class.java).findAll())
    }


    override fun onDestroy() {
        super.onDestroy()
        DataManager.dispose()
        realm.close()
    }
}



