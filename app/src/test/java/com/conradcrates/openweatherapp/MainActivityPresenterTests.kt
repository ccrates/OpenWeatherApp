package com.conradcrates.openweatherapp

import com.conradcrates.openweatherapp.backend.WeatherProvider
import com.conradcrates.openweatherapp.models.CurrentWeatherData
import com.conradcrates.openweatherapp.models.WeatherData
import com.conradcrates.openweatherapp.screens.MainActivityPresenter
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test

class MainActivityPresenterTests {

    private val UNTESTED_LONG = 404L
    private val UNTESTED_FLOAT = 4.04f

    private val mockedWeatherProvider = mock<WeatherProvider>()
    private val mockedView = mock<MainActivityPresenter.View>()

    private val stubbedCachedWeatherData = createStubbedWeatherData("cached")
    private val stubbedFetchedWeatherData = createStubbedWeatherData("fetched")

    private val backendTask = TestBackendTask(stubbedFetchedWeatherData, {})

    private val sut = MainActivityPresenter(mockedView, mockedWeatherProvider)

    private fun createStubbedWeatherData(state: String): WeatherData{
        return WeatherData(
            state, state,
            CurrentWeatherData(
                UNTESTED_LONG, UNTESTED_LONG, UNTESTED_LONG, UNTESTED_FLOAT, UNTESTED_FLOAT,
                UNTESTED_FLOAT, arrayOf()
            )
        )
    }

    @Before
    fun setup(){
        whenever(mockedWeatherProvider.getCachedWeatherData()).thenReturn(stubbedCachedWeatherData)
        whenever(mockedWeatherProvider.fetchWeatherData()).thenReturn(backendTask)
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
    // THEN check to see if there is cached weather data
    // AND display that weather data
    // AND do not show the progress spinner
    @Test
    fun setup_getCachedData_andDisplay(){
        sut.setup()

        verify(mockedWeatherProvider).getCachedWeatherData()
        verify(mockedView).showWeatherData(stubbedCachedWeatherData.current)
        verify(mockedView, times(0)).showProgressSpinner()
    }

    // GIVEN the weather provider does not have cached data
    // WHEN setup is called
    // THEN do not show the weather data
    // AND show the progress spinner
    @Test
    fun setup_noCachedData_doNotDisplay_showProgressSpinner(){
        // Setting backend task to fail as we don't want it interfering with the caching test
        backendTask.toFail()
        whenever(mockedWeatherProvider.getCachedWeatherData()).thenReturn(null)

        sut.setup()

        verify(mockedView, times(0)).showWeatherData(any())
        verify(mockedView).showProgressSpinner()
    }

    // GIVEN fetchWeatherData has been called
    // WHEN fetchWeatherData returns a value
    // THEN display the newly fetched weather data
    @Test
    fun fetchWeatherData_FetchedWeatherData_displayWeatherData(){
        sut.fetchWeatherData()

        verify(mockedView).showWeatherData(stubbedFetchedWeatherData.current)
    }
}