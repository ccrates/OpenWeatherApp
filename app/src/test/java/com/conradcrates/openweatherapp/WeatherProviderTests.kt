package com.conradcrates.openweatherapp

import com.conradcrates.openweatherapp.backend.WeatherProvider
import com.conradcrates.openweatherapp.backend.WeatherProviderService
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class WeatherProviderTests {

    private val mockedWeatherProviderService = mock<WeatherProviderService>()

    private val sut = WeatherProvider(mockedWeatherProviderService)

    // GIVEN the weather provider
    // WHEN fetchWeatherData is called
    // THEN fetch online data
    @Test
    fun fetchWeatherData_fetchWeatherData(){
        sut.fetchWeatherData()

        verify(mockedWeatherProviderService).fetchWeatherData()
    }
}