package com.example.weathertest.model.model.database.repository

import androidx.lifecycle.MutableLiveData
import com.example.weathertest.model.model.database.tables.HourWeatherCondition
import com.example.weathertest.model.model.database.tables.WeatherCondition
import com.example.weathertest.model.model.database.tables.WeekWeatherCondition
import com.example.weathertest.model.model.pojo.DayOfWeek
import com.example.weathertest.model.model.pojo.HourInDay
import com.example.weathertest.model.model.pojo.MainPojo
import io.realm.RealmResults

interface DatabaseAccess {
    fun saveActualWeather(mainPojo: MainPojo)
    fun saveHourlyWeather(hourly: List<HourInDay>)
    fun saveWeeklyWeather(days: List<DayOfWeek?>?)
    fun deleteAll()
    fun getActualWeather(): RealmResults<WeatherCondition>
    fun getHourlyWeather(): RealmResults<HourWeatherCondition>
    fun getWeeklyWeather(): RealmResults<WeekWeatherCondition>
}