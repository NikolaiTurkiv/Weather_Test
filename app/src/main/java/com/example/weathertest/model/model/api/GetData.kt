package com.example.weathertest.model.model.api

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weathertest.model.model.database.db_config.WeatherRealm
import com.example.weathertest.model.model.database.repository.DatabaseWeatherInstance
import com.example.weathertest.viewModel.CurrentWeatherViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.realm.Realm

class GetData(private val databaseWeatherInstance:DatabaseWeatherInstance) {

    private lateinit var disposable: Disposable

    fun loadData(latit: Double?, longt: Double?) {
        disposable = RetrofitInstance.instance.getWeather(latitude = latit, longitude = longt)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                databaseWeatherInstance.deleteAll()
                Log.i("DATABASE","1")
                databaseWeatherInstance.saveActualWeather(it)
                Log.i("DATABASE", "LOAD")
                Log.i("RETROFIT",it.timezone.toString())
                databaseWeatherInstance.saveHourlyWeather(it.hourly?.data!!)
                databaseWeatherInstance.saveWeeklyWeather(it.daily?.data)
            }, {
                Log.i("ERRROR",it.message.toString())
            }, {

            })
    }

    fun dispose(){
        disposable.dispose()
    }
}