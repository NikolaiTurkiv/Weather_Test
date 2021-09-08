package com.example.weathertest.model.model.pojo

import com.google.gson.annotations.SerializedName

class MainPojo {

    @SerializedName("timezone")
    val timezone: String? = null

    @SerializedName("currently")
    val currently: Currently? = null

    @SerializedName("hourly")
    val hourly: Hourly? = null

    @SerializedName("daily")
    val daily: Daily? = null

}