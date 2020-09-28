package com.conradcrates.openweatherapp

import android.app.Activity
import com.conradcrates.openweatherapp.location.LocationProvider
import com.conradcrates.openweatherapp.location.LocationProviderService
import com.conradcrates.openweatherapp.models.LocationData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LocationProviderTests {

    // The lat/long of the London Eye if you must know
    private val TEST_LATITUDE = "51.503471"
    private val TEST_LONGITUDE = "-0.119420"

    private val mockedLocationProviderService = mock<LocationProviderService>()
    private val mockedActivity = mock<Activity>()

    private val sut = LocationProvider(mockedLocationProviderService, mockedActivity)

    @Before
    fun setup(){
        whenever(mockedLocationProviderService.fetchLocation()).thenReturn(LocationData(TEST_LATITUDE, TEST_LONGITUDE))
    }

    // GIVEN GPS permission has been enabled
    // WHEN fetchLocation is called
    // THEN provide location data
    @Test
    fun getLocation_returnLocationData(){
        val result = sut.getLocation()

        Assert.assertEquals(TEST_LATITUDE, result.lat)
        Assert.assertEquals(TEST_LONGITUDE, result.long)
    }

    // GIVEN GPS permission has not been enabled
    // WHEN fetchLocation is called
    // THEN do not provide location data
    @Test
    fun getLocation_noGpsPermission_returnNothing(){
        val result = sut.getLocation()

        Assert.assertNull(result)
    }
}