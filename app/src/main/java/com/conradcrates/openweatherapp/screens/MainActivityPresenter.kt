package com.conradcrates.openweatherapp.screens

import com.conradcrates.openweatherapp.backend.WeatherProvider
import com.conradcrates.openweatherapp.models.WeatherData

class MainActivityPresenter (
    private val view: View,
    private val weatherProvider: WeatherProvider
){

    fun setup(){
        val cachedData = weatherProvider.getCachedWeatherData()

        if(cachedData != null){
            view.showWeatherData(cachedData)
        } else {
            view.showProgressSpinner()
        }

        weatherProvider.fetchWeatherData().addOnSuccessListener {
            view.showWeatherData(it)
        }
    }

    interface View{

        fun showProgressSpinner()

        fun showWeatherData(weatherData: WeatherData)
    }
}