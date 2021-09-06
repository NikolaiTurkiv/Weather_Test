package com.example.weathertest.database

import com.example.weathertest.pojo.DayOfWeek
import com.example.weathertest.pojo.HourInDay
import com.example.weathertest.pojo.MainPojo
import io.realm.RealmList
import io.realm.RealmObject

open class WeatherCondition : RealmObject() {

    var actualLocation:String? = null
    var actualTemperature = 0.0
    var actualHumidity = 0.0
    var actualUVindex = 0.0
    var actualWind = 0.0
    var actualPressure = 0.0

}