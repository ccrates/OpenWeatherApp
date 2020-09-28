package com.conradcrates.openweatherapp

import com.conradcrates.openweatherapp.location.LocationProvider
import com.conradcrates.openweatherapp.location.LocationProviderService
import com.conradcrates.openweatherapp.models.LocationData
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LocationProviderTests {

    // The lat/long of the London Eye if you must know
    private val TEST_LATITUDE = 51.503471
    private val TEST_LONGITUDE = -0.119420

    private val mockedLocationProviderService = mock<LocationProviderService>()

    private val callbackCaptor = argumentCaptor<(LocationData) -> Unit>()

    private val sut = LocationProvider(mockedLocationProviderService)

    @Before
    fun setup() {
        whenever(mockedLocationProviderService.fetchLocation(callbackCaptor.capture())).then {
            callbackCaptor.firstValue.invoke(LocationData(TEST_LATITUDE, TEST_LONGITUDE))
        }
    }

    // GIVEN GPS permission has been enabled
    // WHEN fetchLocation is called
    // THEN provide location data
    @Test
    fun getLocation_returnLocationData() {
        runBlocking {
            suspendCoroutine<Unit> { continuation ->
                sut.fetchLocation {
                    Assert.assertEquals(TEST_LATITUDE, it.lat, 0.1)
                    Assert.assertEquals(TEST_LONGITUDE, it.long, 0.1)
                    continuation.resume(Unit)
                }
            }
        }
    }
}