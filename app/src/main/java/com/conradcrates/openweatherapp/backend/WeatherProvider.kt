package com.conradcrates.openweatherapp.backend

import com.conradcrates.openweatherapp.models.WeatherData

class WeatherProvider(
    private val weatherService: WeatherProviderService
) {

    fun fetchWeatherData(){
        weatherService.fetchWeatherData()
    }
}