package com.conradcrates.openweatherapp

class MainActivityPresenter (
    private val weatherProvider: WeatherProvider){

    fun setup(){
        weatherProvider.fetchWeatherData()
    }
}