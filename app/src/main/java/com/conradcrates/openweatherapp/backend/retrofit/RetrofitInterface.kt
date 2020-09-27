package com.conradcrates.openweatherapp.backend.retrofit

import com.conradcrates.openweatherapp.models.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("onecall?")
    fun fetchWeatherData(
        @Query("lat") lat: String,
        @Query("lon") long: String,
        @Query("appid") apiKey: String,
        @Query("exclude") excludeCSV: String?,
        @Query("units") units: String
    ): Call<WeatherData>
}