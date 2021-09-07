package com.example.weathertest.model.model.database.tables

import io.realm.RealmObject

open class WeatherCondition : RealmObject() {

    var actualLocation:String? = null
    var actualTemperature = 0.0
    var actualHumidity = 0.0
    var actualUVindex = 0.0
    var actualWind = 0.0
    var actualPressure = 0.0
    var icon:String? = null

}