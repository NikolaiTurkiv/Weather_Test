package com.example.weathertest.database

import io.realm.Realm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class HourWeatherCondition : RealmObject() {

    var hourTime = 0
    var hourTemperature = 0.0
    var icon: String? = null

}