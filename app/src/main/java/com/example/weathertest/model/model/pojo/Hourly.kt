package com.example.weathertest.model.model.pojo

import com.google.gson.annotations.SerializedName

class Hourly {

    @SerializedName("icon")
    val icon: String? = null

    @SerializedName("data")
    val data: List<HourInDay>? = null

}