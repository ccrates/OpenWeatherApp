package com.conradcrates.openweatherapp

class MainActivityPresenter (
    private val view: View,
    private val weatherProvider: WeatherProvider){

    fun setup(){
        view.showProgressSpinner()
        weatherProvider.fetchWeatherData()
    }

    interface View{

        fun showProgressSpinner()
    }
}