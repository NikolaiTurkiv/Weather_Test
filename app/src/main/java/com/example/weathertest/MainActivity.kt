package com.example.weathertest


import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.weathertest.api.RetrofitInstance
import com.google.android.gms.location.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    val any:Any = Any()

    var PERMISSION_ID = 1000



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        getLastLocation()


        }

    private fun getLastLocation(){
        if(checkPermission()){
            if(isLocationEnabled()){
                fusedLocationProviderClient.lastLocation.addOnCompleteListener {
                    val location:Location? = it.result
                    if(location == null){
                        getNewLocation()
                    }else{
//                        longitude = location.longitude
//                        Log.d("DEBUG/long",longitude.toString())
//                        latitude = location.latitude
//                        Log.d("DEBUG/lat",latitude.toString())
                        getRetrofit(location.latitude,location.longitude)
                    }

                }
            }else{
                Toast.makeText(this,"Enable service",Toast.LENGTH_SHORT).show()
            }

        }else{
            requestPermission()
        }
    }

    private fun checkPermission():Boolean{
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true
        }

        return false
    }

    private fun requestPermission(){
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION),PERMISSION_ID)
    }

    private fun isLocationEnabled():Boolean{

        var locationManager:LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == PERMISSION_ID){
            if(grantResults.isNotEmpty()&&grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.d("DEBUG","HAVE PERMISSION")
            }
        }

    }

    private val locationCallback = object: LocationCallback(){
        override fun onLocationResult(p0: LocationResult) {
            var location = p0.lastLocation
//            longitude = location.longitude
//            Log.d("DEBUG",longitude.toString())
//            latitude = location.latitude
//            Log.d("DEBUG",latitude.toString())


        }
    }

    private fun getNewLocation(){
        locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 2
        if(checkPermission())
        fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper())

    }



    override fun onStart() {
        super.onStart()

    }

    fun getRetrofit(lat:Double,longt:Double){
        RetrofitInstance.instance.getWeather(lat, longt)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("IINFO", it.currently?.temperature.toString())
                Log.i("IINFO", it.latitude.toString())
                Log.i("IINFO", it.longitude.toString())
                Log.i("IINFO", it.timezone.toString())

            }, {
                println(it.message.toString())
            }, {}
            )
    }
}


