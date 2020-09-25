package com.conradcrates.openweatherapp

class WeatherProvider(
    private val weatherService: WeatherProviderService) {

    fun fetchWeatherData(){
        weatherService.fetchWeatherData()
    }
}