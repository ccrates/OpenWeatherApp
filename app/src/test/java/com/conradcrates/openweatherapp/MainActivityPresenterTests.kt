package com.conradcrates.openweatherapp

import com.conradcrates.openweatherapp.backend.WeatherProvider
import com.conradcrates.openweatherapp.models.WeatherData
import com.conradcrates.openweatherapp.screens.MainActivityPresenter
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test

class MainActivityPresenterTests {

    private val UNTESTED_STRING = "untestedString"

    private val mockedWeatherProvider = mock<WeatherProvider>()
    private val mockedView = mock<MainActivityPresenter.View>()

    private val stubbedWeatherData = WeatherData(UNTESTED_STRING)

    private val sut = MainActivityPresenter(mockedView, mockedWeatherProvider)

    @Before
    fun setup(){
//        whenever(mockedWeatherProvider.fetchWeatherData()).thenReturn(stubbedWeatherData)
    }

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