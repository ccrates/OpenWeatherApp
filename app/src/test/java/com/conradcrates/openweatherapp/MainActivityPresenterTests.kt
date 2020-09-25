package com.conradcrates.openweatherapp

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class MainActivityPresenterTests {

    private val mockedWeatherProvider = mock<WeatherProvider>()

    // GIVEN the presenter has just been started
    // WHEN setup is called
    // THEN fetch weather data
    @Test
    fun setup_fetchWeatherData(){
        val sut = MainActivityPresenter(mockedWeatherProvider)

        sut.setup()

        verify(mockedWeatherProvider).fetchWeatherData()
    }
}