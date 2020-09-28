package com.conradcrates.openweatherapp.location

import com.conradcrates.openweatherapp.models.LocationData

class LocationProvider(private val locationService: LocationProviderService) {

    fun fetchLocation(callback: (LocationData) -> Unit) {
        locationService.fetchLocation(callback)
    }
}