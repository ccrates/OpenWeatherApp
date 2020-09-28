package com.conradcrates.openweatherapp.location

import com.conradcrates.openweatherapp.models.LocationData

class LocationProvider(private val locationService: LocationProviderService) {

    fun getLocation(): LocationData{
        return locationService.fetchLocation()
    }
}