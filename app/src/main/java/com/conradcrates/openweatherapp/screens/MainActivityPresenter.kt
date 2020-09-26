package com.conradcrates.openweatherapp.screens

import com.conradcrates.openweatherapp.backend.WeatherProvider

class MainActivityPresenter (
    private val view: View,
    private val weatherProvider: WeatherProvider
){

    fun setup(){
        view.showProgressSpinner()
        weatherProvider.fetchWeatherData()
    }

    interface View{

        fun showProgressSpinner()
    }
}