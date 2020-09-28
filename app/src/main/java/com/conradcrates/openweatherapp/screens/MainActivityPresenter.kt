package com.conradcrates.openweatherapp.screens

import com.conradcrates.openweatherapp.R
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
        view.showInfoText(R.string.main_text_fetchingLocation)
        locationProvider.fetchLocation{ location ->
            view.showInfoText(R.string.main_text_fetchingWeather)
            weatherProvider.fetchWeatherData(location.lat, location.long).addOnSuccessListener {
                view.showWeatherData(it.current)
                view.hideInfoText()
            }
        }
    }

    interface View{

        fun showWeatherData(weatherData: CurrentWeatherData)

        fun showInfoText(resourceId: Int)

        fun hideInfoText()
    }
}