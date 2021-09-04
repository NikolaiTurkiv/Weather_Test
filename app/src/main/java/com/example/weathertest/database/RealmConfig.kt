package com.example.weathertest.database

import io.realm.RealmConfiguration

object RealmConfig {
    val config = RealmConfiguration.Builder()
        .name("weather_condition.db")
        .schemaVersion(1)
        .build()
}