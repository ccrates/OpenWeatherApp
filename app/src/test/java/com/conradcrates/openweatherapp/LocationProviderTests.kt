package com.conradcrates.openweatherapp

import com.conradcrates.openweatherapp.location.LocationProvider
import com.conradcrates.openweatherapp.location.LocationProviderService
import com.conradcrates.openweatherapp.models.LocationData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert
import org.junit.Test

class LocationProviderTests {

    private val TEST_LATITUDE = "51.503471"
    private val TEST_LONGITUDE = "-0.119420"

    private val mockedLocationProviderService = mock<LocationProviderService>()

    // GIVEN GPS permission has been enabled
    // WHEN fetchLocation is called
    // THEN provide location data
    @Test
    fun getLocation_returnLocationData(){
        whenever(mockedLocationProviderService.fetchLocation()).thenReturn(LocationData(TEST_LATITUDE, TEST_LONGITUDE))
        val sut = LocationProvider(mockedLocationProviderService)

        val result = sut.getLocation()

        Assert.assertEquals(TEST_LATITUDE, result.lat)
        Assert.assertEquals(TEST_LONGITUDE, result.long)
    }
}