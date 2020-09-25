package com.conradcrates.openweatherapp

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class MainActivityPresenterTests {

    private val mockedWeatherProvider = mock<WeatherProvider>()
    private val mockedView = mock<MainActivityPresenter.View>()

    private val sut = MainActivityPresenter(mockedView, mockedWeatherProvider)

    // GIVEN the presenter has just been started
    // WHEN setup is called
    // THEN fetch weather data
    @Test
    fun setup_fetchWeatherData(){
        sut.setup()

        verify(mockedWeatherProvider).fetchWeatherData()
    }

    // GIVEN the presenter has just been started
    // WHEN setup is called
    // THEN show the progress spinner
    @Test
    fun setup_showProgressSpinner(){
        sut.setup()

        verify(mockedView).showProgressSpinner()
    }
}