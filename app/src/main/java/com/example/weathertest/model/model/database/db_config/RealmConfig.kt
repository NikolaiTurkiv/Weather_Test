package com.example.weathertest.model.model.database.db_config

import io.realm.RealmConfiguration

object RealmConfig {
    val config = RealmConfiguration.Builder()
        .name("weather_condition.db")
        .deleteRealmIfMigrationNeeded()
        .schemaVersion(3)
        .build()
}