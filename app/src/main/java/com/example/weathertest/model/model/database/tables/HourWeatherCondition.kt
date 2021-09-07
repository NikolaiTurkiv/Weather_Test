package com.example.weathertest.model.model.database.tables

import io.realm.RealmObject

open class HourWeatherCondition : RealmObject() {

    var hourTime = 0
    var hourTemperature = 0.0
    var icon: String? = null

}