package com.conradcrates.openweatherapp.backend

import com.conradcrates.openweatherapp.models.WeatherData

class WeatherProvider(
    private val weatherService: WeatherProviderService
) {

    private val cachedWeatherData: WeatherData? = null

    fun getCachedWeatherData(): WeatherData? {
        return cachedWeatherData
    }

    fun fetchWeatherData(): BackendTask<WeatherData>{
        return weatherService.fetchWeatherData()
    }
}