package com.example.weathertest.model.model

import androidx.lifecycle.MutableLiveData
import com.example.weathertest.model.model.api.GetData
import com.example.weathertest.model.model.database.repository.DatabaseWeatherInstance
import com.example.weathertest.model.model.database.tables.HourWeatherCondition
import com.example.weathertest.model.model.database.tables.WeatherCondition
import com.example.weathertest.model.model.database.tables.WeekWeatherCondition
import io.realm.Realm
import io.realm.RealmResults

object DataManager{

        private val databaseWeatherInstance = DatabaseWeatherInstance()
        private val getData:GetData = GetData(databaseWeatherInstance)

        fun getWeatherData(latitude:Double?,longitude:Double?){
            getData.loadData(latitude,longitude)
        }
    fun dispose(){
        getData.dispose()
    }

     fun getHourlyWeather(): RealmResults<HourWeatherCondition> {
        return databaseWeatherInstance.getHourlyWeather()
     }
     fun getWeeklyWeather(): RealmResults<WeekWeatherCondition> {
        return databaseWeatherInstance.getWeeklyWeather()
     }
     fun getActualWeather() : RealmResults<WeatherCondition> {
        return databaseWeatherInstance.getActualWeather()
     }

}