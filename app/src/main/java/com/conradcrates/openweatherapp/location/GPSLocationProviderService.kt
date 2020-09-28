package com.conradcrates.openweatherapp.location

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.conradcrates.openweatherapp.models.LocationData

class GPSLocationProviderService: LocationProviderService, LocationListener {

    var activity: Activity? = null
    set(value){
        field = value
        prepareLocationManager()
    }

    @SuppressWarnings("MissingPermission")
    private fun prepareLocationManager(){
        if (isLocationPermissionEnabled()) {
            val locationManager =
                activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this)
        } else {
            requestLocationPermission()
        }
    }

    private fun isLocationPermissionEnabled(): Boolean{
        return ContextCompat.checkSelfPermission(
            activity!!, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            activity!!,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            101
        )
    }

    private val locationListeners = ArrayList<(LocationData) -> Unit>()
    private var lastKnownLocation: Location? = null

    override fun fetchLocation(callback: (LocationData) -> Unit) {
        prepareLocationManager()
        locationListeners.add(callback)
        lastKnownLocation?.let {
            callback.invoke(LocationData(it.latitude, it.longitude))
        }
    }

    override fun onLocationChanged(location: Location?) {
        lastKnownLocation = location
        location?.let {
            for (listener in locationListeners) {
                listener.invoke(LocationData(it.latitude, it.longitude))
            }
            locationListeners.clear()
        }
    }

    override fun onProviderEnabled(p0: String?) {
    }

    override fun onProviderDisabled(p0: String?) {
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
    }
}