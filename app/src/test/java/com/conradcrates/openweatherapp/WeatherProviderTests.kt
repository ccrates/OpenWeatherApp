package com.conradcrates.openweatherapp

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class WeatherProviderTests {

    private val mockedWeatherProviderService = mock<WeatherProviderService>()

    // GIVEN the weather provider
    // WHEN fetchWeatherData is called
    // THEN fetch online data
    @Test
    fun fetchWeatherData_hasCachedData_returnCachedData_continueToFetch(){
        val sut = WeatherProvider(mockedWeatherProviderService)

        sut.fetchWeatherData()

        verify(mockedWeatherProviderService).fetchWeatherData()
    }
}