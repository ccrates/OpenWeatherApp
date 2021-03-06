package com.conradcrates.openweatherapp.models

import java.io.Serializable

data class WeatherData(
    val lat: String,
    val lon: String,
    val current: CurrentWeatherData
) : Serializable

data class CurrentWeatherData(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: Float,
    val feels_like: Float,
    val wind_speed: Float,
    val weather: Array<WeatherSnapshot>
): Serializable

data class WeatherSnapshot(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
): Serializable