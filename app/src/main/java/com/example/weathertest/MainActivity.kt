package com.example.weathertest


import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.os.HandlerThread
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.weathertest.WeekWeatherFragment.Companion.TAG_TO_BOTTOM_FRAGMENT
import com.example.weathertest.api.RetrofitInstance
import com.example.weathertest.database.HourWeatherCondition
import com.example.weathertest.database.WeatherCondition
import com.example.weathertest.databinding.ActivityMainBinding
import com.google.android.gms.location.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.realm.Realm
import java.util.*

class MainActivity : AppCompatActivity() {

    private var longitude: Double? = 0.0
    private var latitude: Double? = 0.0
    private lateinit var realm: Realm
    private lateinit var weatherViewModel: WeatherViewModel
    val PERMISSION_ID = 1000
    private lateinit var  bottomSheetDialogFragment:BottomSheetDialogFragment



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        realm = Realm.getDefaultInstance()
        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        if(!checkPermission()){
            requestPermission()
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.actualWeatherContainer, ActualWeatherFragment())
            .replace(R.id.hourWeatherContainer, HourlyWeatherFragment())
            .replace(R.id.bottom_sheet,WeekWeatherFragment())
           // .show(WeekWeatherFragment())
            .commit()
//        bottomSheetDialogFragment = BottomSheetDialogFragment()
//        bottomSheetDialogFragment.show(supportFragmentManager,TAG_TO_BOTTOM_FRAGMENT)

    }


    override fun onStart() {
        super.onStart()
        val lm = getSystemService(LOCATION_SERVICE) as LocationManager
        if (checkPermission()) {
            val location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            longitude = location?.longitude
            latitude = location?.latitude
            Log.i("NEW LOCATION", "${longitude},${latitude}")
        }
        weatherViewModel.loadData(latitude, longitude)
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

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            PERMISSION_ID
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}

