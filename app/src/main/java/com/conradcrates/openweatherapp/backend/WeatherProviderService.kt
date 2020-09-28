package com.conradcrates.openweatherapp.backend

import com.conradcrates.openweatherapp.models.WeatherData

interface WeatherProviderService {

    fun fetchWeatherData(lat: Double, long: Double) : BackendTask<WeatherData>
}