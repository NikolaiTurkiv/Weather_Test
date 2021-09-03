package com.example.weathertest.api

import com.example.weathertest.pojo.MainPojo
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiFactory {
    @GET("897d345658071ee124f3181474b68389/{latitude},{longitude}")
    fun getWeather(
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double
    ): Observable<MainPojo>

}