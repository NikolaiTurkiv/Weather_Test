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
        Log.i("DATABASE", "getactualweather")
    }
    fun getHourlyWeather(){
        hourlyWeatherViewModel.value = DataManager.getHourlyWeather()
    }
    fun getWeeklyWeather(){
        weeklyWeatherViewModel.value = DataManager.getWeeklyWeather()
    }
}