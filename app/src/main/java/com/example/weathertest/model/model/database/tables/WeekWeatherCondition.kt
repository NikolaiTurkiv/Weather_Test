package com.example.weathertest.model.model.database.tables

import io.realm.RealmObject

open class WeekWeatherCondition : RealmObject() {

    var time = 0
    var icon:String? = null
    var minTemperature = 0.0
    var maxTemperature = 0.0
    var wind = 0.0
    var humidity = 0.0

}