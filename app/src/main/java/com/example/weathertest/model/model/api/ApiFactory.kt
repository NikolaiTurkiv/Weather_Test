package com.example.weathertest.model.model.api

import com.example.weathertest.model.model.pojo.MainPojo
import com.example.weathertest.utils.Constants
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiFactory {
    @GET("{key}/{latitude},{longitude}")
    fun getWeather(
        @Path("key") key:String = Constants.API_KEY,
        @Path("latitude") latitude: Double?,
        @Path("longitude") longitude: Double?
    ): Observable<MainPojo>

}