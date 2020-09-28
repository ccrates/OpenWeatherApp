package com.conradcrates.openweatherapp.backend

import android.content.Context
import com.conradcrates.openweatherapp.constants.AppConstants
import com.conradcrates.openweatherapp.models.WeatherData
import java.io.*
import java.lang.Exception

class WeatherProvider(
    private val weatherService: WeatherProviderService,
    private val context: Context
) {

    fun getCachedWeatherData(): WeatherData? {
        return readDataFromCache()
    }

    private fun writeDataToCache(weatherData: WeatherData){
        val file = File(context.cacheDir, AppConstants.FILE_CACHED_WEATHER_DATA)
        val fos = FileOutputStream(file)
        val oos = ObjectOutputStream(fos)
        oos.writeObject(weatherData)
        oos.flush()
        oos.close()
    }

    private fun readDataFromCache(): WeatherData? {
        try {
            val file = File(context.cacheDir, AppConstants.FILE_CACHED_WEATHER_DATA)
            val fis = FileInputStream(file)
            val ois = ObjectInputStream(fis)
            val weatherData = ois.readObject() as WeatherData?
            ois.close()
            return weatherData
        } catch (e: Exception){
            // Most likely a file not found exception. Can keep silent for now.
        }
        return null
    }

    fun fetchWeatherData(lat: Double, long: Double): BackendTask<WeatherData>{
        return weatherService.fetchWeatherData(lat, long)
            .addOnSuccessListener { writeDataToCache(it) }
    }
}