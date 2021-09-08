package com.example.weathertest.model.model.pojo

import com.google.gson.annotations.SerializedName

class Currently {

    @SerializedName("time")
    val time = 0

    @SerializedName("icon")
    val icon: String? = null

    @SerializedName("temperature")
    val temperature = 0.0

    @SerializedName("humidity")
    val humidity = 0.0

    @SerializedName("pressure")
    val pressure = 0.0

    @SerializedName("windSpeed")
    val windSpeed = 0.0

    @SerializedName("uvIndex")
    val uvIndex = 0.0

}