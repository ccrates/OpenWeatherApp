package com.conradcrates.openweatherapp

import android.content.Context
import com.conradcrates.openweatherapp.backend.WeatherProvider
import com.conradcrates.openweatherapp.backend.WeatherProviderService
import com.conradcrates.openweatherapp.models.WeatherData
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test

class WeatherProviderTests {

    private val UNTESTED_DOUBLE = 4.04

    private val mockedWeatherProviderService = mock<WeatherProviderService>()
    private val mockedWeatherData = mock<WeatherData>()
    private val mockedContext = mock<Context>()

    private val sut = WeatherProvider(mockedWeatherProviderService, mockedContext)

    // GIVEN the weather provider
    // WHEN fetchWeatherData is called
    // THEN fetch online data
    @Test
    fun fetchWeatherData_fetchWeatherData(){
        whenever(mockedWeatherProviderService.fetchWeatherData(any(), any())).thenReturn(TestBackendTask(mockedWeatherData){})

        sut.fetchWeatherData(UNTESTED_DOUBLE, UNTESTED_DOUBLE)

        verify(mockedWeatherProviderService).fetchWeatherData(UNTESTED_DOUBLE, UNTESTED_DOUBLE)
    }
}