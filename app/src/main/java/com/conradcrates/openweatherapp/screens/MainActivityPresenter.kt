package com.conradcrates.openweatherapp.screens

import com.conradcrates.openweatherapp.backend.WeatherProvider
import com.conradcrates.openweatherapp.location.LocationProvider
import com.conradcrates.openweatherapp.models.CurrentWeatherData

class MainActivityPresenter (
    private val view: View,
    private val weatherProvider: WeatherProvider,
    private val locationProvider: LocationProvider
){

    fun setup(){
        val cachedData = weatherProvider.getCachedWeatherData()

        if(cachedData != null) {
            view.showWeatherData(cachedData.current)
        }

        fetchWeatherData()
    }

    fun fetchWeatherData(){
        locationProvider.getLocation()?.let { location ->
            view.showProgressSpinner()
            weatherProvider.fetchWeatherData(location.lat, location.long).addOnSuccessListener {
                view.showWeatherData(it.current)
                view.hideProgressSpinner()
            }
        }
    }

    interface View{

        fun showWeatherData(weatherData: CurrentWeatherData)

        fun showProgressSpinner()

        fun hideProgressSpinner()
    }
}