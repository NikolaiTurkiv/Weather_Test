package com.example.weathertest.model.model.database.repository

import androidx.lifecycle.MutableLiveData
import com.example.weathertest.model.model.database.tables.HourWeatherCondition
import com.example.weathertest.model.model.database.tables.WeatherCondition
import com.example.weathertest.model.model.database.tables.WeekWeatherCondition
import com.example.weathertest.model.model.pojo.DayOfWeek
import com.example.weathertest.model.model.pojo.HourInDay
import com.example.weathertest.model.model.pojo.MainPojo
import io.realm.Realm
import io.realm.RealmResults

class DatabaseWeatherInstance() : DatabaseAccess {

    val realm = Realm.getDefaultInstance()

    override fun saveActualWeather(mainPojo: MainPojo) {
        realm.beginTransaction()
        val weather = WeatherCondition().apply {
            actualLocation = mainPojo.timezone
            actualHumidity = mainPojo.currently?.humidity!!
            actualPressure = mainPojo.currently.pressure
            actualTemperature = mainPojo.currently.temperature
            actualUVindex = mainPojo.currently.uvIndex
            actualWind = mainPojo.currently.windSpeed
            icon = mainPojo.currently.icon
        }
        realm.insert(weather)
        realm.commitTransaction()

    }

    override fun saveHourlyWeather(hourly: List<HourInDay>) {
        for (hour in hourly) {
            realm.beginTransaction()
            val weatherHour = HourWeatherCondition().apply {
                hourTime = hour.time
                hourTemperature = hour.temperature
                icon = hour.icon
            }
            realm.insert(weatherHour)
            realm.commitTransaction()
        }

    }

    override fun saveWeeklyWeather(days: List<DayOfWeek?>?) {
        if (days != null) {
            for (day in days) {
                realm.beginTransaction()
                val dayOfWeek = WeekWeatherCondition().apply {
                    time = day?.time!!
                    icon = day.icon
                    minTemperature = day.temperatureLow
                    maxTemperature = day.temperatureHigh
                    wind = day.windSpeed
                    humidity = day.humidity
                }
                realm.insert(dayOfWeek)
                realm.commitTransaction()
            }
        }

    }

    override fun deleteAll() {
        realm.beginTransaction()
        realm.deleteAll()
        realm.commitTransaction()
    }

    override fun getActualWeather(): RealmResults<WeatherCondition> {
        return realm.where(WeatherCondition::class.java).findAll()
    }

    override fun getHourlyWeather(): RealmResults<HourWeatherCondition> {

        return realm.where(HourWeatherCondition::class.java).findAll()
    }

    override fun getWeeklyWeather(): RealmResults<WeekWeatherCondition> {

        return realm.where(WeekWeatherCondition::class.java).findAll()
    }

}