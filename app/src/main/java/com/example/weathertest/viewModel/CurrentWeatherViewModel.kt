package com.example.weathertest.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weathertest.model.model.DataManager
import com.example.weathertest.model.model.api.RetrofitInstance
import com.example.weathertest.model.model.database.tables.HourWeatherCondition
import com.example.weathertest.model.model.database.tables.WeatherCondition
import com.example.weathertest.model.model.database.tables.WeekWeatherCondition
import com.example.weathertest.model.model.pojo.DayOfWeek
import com.example.weathertest.model.model.pojo.HourInDay
import com.example.weathertest.model.model.pojo.MainPojo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.realm.Realm

class CurrentWeatherViewModel() : ViewModel() {


    val actualWeatherViewModel : MutableLiveData<List<WeatherCondition>> by lazy{
        MutableLiveData<List<WeatherCondition>>()
    }

    val hourlyWeatherViewModel:MutableLiveData<List<HourWeatherCondition>> by lazy{
        MutableLiveData<List<HourWeatherCondition>>()
    }

    val weeklyWeatherViewModel:MutableLiveData<List<WeekWeatherCondition>> by lazy{
        MutableLiveData<List<WeekWeatherCondition>>()
    }


    fun getActualWeather(){
      actualWeatherViewModel.value = DataManager.getActualWeather()
    }
    fun getHourlyWeather(){
        hourlyWeatherViewModel.value = DataManager.getHourlyWeather()
    }
    fun getWeeklyWeather(){
        weeklyWeatherViewModel.value = DataManager.getWeeklyWeather()
    }



//    fun saveActualWeather(mainPojo: MainPojo) {
//        var realm = Realm.getDefaultInstance()
//        realm.beginTransaction()
//        val weather = WeatherCondition().apply {
//            actualLocation = mainPojo.timezone
//            actualHumidity = mainPojo.currently?.humidity!!
//            actualPressure = mainPojo.currently.pressure
//            actualTemperature = mainPojo.currently.temperature
//            actualUVindex = mainPojo.currently.uvIndex
//            actualWind = mainPojo.currently.windSpeed
//            icon = mainPojo.currently.icon
//        }
//        realm.insert(weather)
//        realm.commitTransaction()
//
//    }
//
//    fun getActualWeather():MutableLiveData<List<WeatherCondition>>{
//        var realm = Realm.getDefaultInstance()
//
//            actualWeatherViewModel.value = realm.where(WeatherCondition::class.java).findAll()
//
//        return actualWeatherViewModel
//    }
//
//    fun saveHourlyWeather(hourly: List<HourInDay>) {
//        var realm = Realm.getDefaultInstance()
//        for (hour in hourly) {
//            realm.beginTransaction()
//            val weatherHour = HourWeatherCondition().apply {
//                hourTime = hour.time
//                hourTemperature = hour.temperature
//                icon = hour.icon
//            }
//            realm.insert(weatherHour)
//            realm.commitTransaction()
//        }
//        hourlyWeatherViewModel.value = realm.where(HourWeatherCondition::class.java).findAll()
//    }
//
//    fun saveWeeklyWeather(days: List<DayOfWeek?>?){
//        var realm = Realm.getDefaultInstance()
//        if (days != null) {
//            for(day in days){
//                realm.beginTransaction()
//                val dayOfWeek = WeekWeatherCondition().apply {
//                    time = day?.time!!
//                    icon = day.icon
//                    minTemperature = day.temperatureLow
//                    maxTemperature = day.temperatureHigh
//                    wind = day.windSpeed
//                    humidity = day.humidity
//                }
//                realm.insert(dayOfWeek)
//                realm.commitTransaction()
//            }
//        }
//        weeklyWeatherViewModel.value = realm.where(WeekWeatherCondition::class.java).findAll()
//    }
//
//    fun deleteAll(){
//        var realm = Realm.getDefaultInstance()
//        realm.beginTransaction()
//        realm.deleteAll()
//        realm.commitTransaction()
//    }
//
//    private lateinit var disposable: Disposable
//
//    fun loadData(latit: Double?, longt: Double?) {
//        disposable = RetrofitInstance.instance.getWeather(latit, longt)
//            .subscribeOn(Schedulers.newThread())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                deleteAll()
//                saveActualWeather(it)
//                saveHourlyWeather(it.hourly?.data!!)
//                saveWeeklyWeather(it.daily?.data)
//            }, {
//               Log.i("ERRROR",it.message.toString())
//
//            }, {
//
//            })
//    }


}