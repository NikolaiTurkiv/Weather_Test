package com.example.weathertest.database

import android.app.Application
import io.realm.Realm

class WeatherRealm : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfig.config)


    }
}