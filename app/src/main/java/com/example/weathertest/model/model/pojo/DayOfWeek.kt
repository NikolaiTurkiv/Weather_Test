package com.example.weathertest.model.model.pojo

import com.google.gson.annotations.SerializedName

class DayOfWeek  {

    @SerializedName("time")
    val time = 0

    @SerializedName("icon")
    val icon: String? = null

    @SerializedName("temperatureHigh")
    val temperatureHigh = 0.0

    @SerializedName("temperatureLow")
    val temperatureLow = 0.0

    @SerializedName("humidity")
    val humidity = 0.0

    @SerializedName("windSpeed")
    val windSpeed = 0.0

}