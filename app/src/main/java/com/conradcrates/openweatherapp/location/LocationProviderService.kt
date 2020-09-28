package com.conradcrates.openweatherapp.location

import com.conradcrates.openweatherapp.models.LocationData

interface LocationProviderService {

    fun fetchLocation(): LocationData
}