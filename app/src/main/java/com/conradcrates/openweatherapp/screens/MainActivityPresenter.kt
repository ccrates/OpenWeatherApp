package com.conradcrates.openweatherapp.screens

import com.conradcrates.openweatherapp.backend.WeatherProvider
import com.conradcrates.openweatherapp.models.CurrentWeatherData

class MainActivityPresenter (
    private val view: View,
    private val weatherProvider: WeatherProvider
){

    fun setup(){
        val cachedData = weatherProvider.getCachedWeatherData()

        if(cachedData != null){
            view.showWeatherData(cachedData.current)
        } else {
            view.showProgressSpinner()
        }

        fetchWeatherData()
    }

    fun fetchWeatherData(){
        weatherProvider.fetchWeatherData().addOnSuccessListener {
            view.showWeatherData(it.current)
        }
    }

    interface View{

        fun showProgressSpinner()

        fun showWeatherData(weatherData: CurrentWeatherData)
    }
}