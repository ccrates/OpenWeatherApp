package com.conradcrates.openweatherapp.screens

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.conradcrates.openweatherapp.R
import com.conradcrates.openweatherapp.backend.WeatherProvider
import com.conradcrates.openweatherapp.backend.retrofit.RetrofitWeatherProviderService
import com.conradcrates.openweatherapp.location.GPSLocationProviderService
import com.conradcrates.openweatherapp.location.LocationProvider
import com.conradcrates.openweatherapp.models.CurrentWeatherData
import com.conradcrates.openweatherapp.utils.DateTimeConverter
import com.conradcrates.openweatherapp.utils.NetworkManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityPresenter.View {

    // TODO Dependency injection here would want to be improved
    private val locationProviderService = GPSLocationProviderService()
    private val presenter = MainActivityPresenter(
        this,
        WeatherProvider(RetrofitWeatherProviderService(), this),
        LocationProvider(locationProviderService),
        NetworkManager(this)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            presenter.fetchWeatherData()
        }

        locationProviderService.activity = this

        presenter.setup()
    }

    override fun showWeatherData(weatherData: CurrentWeatherData) {
        val temperatureText = "${weatherData.temp}°c"
        val windSpeedText = "${weatherData.wind_speed} km/h"
        text_condition_value.text = weatherData.weather[0].description
        text_temperature_value.text = temperatureText
        text_windspeed_value.text = windSpeedText
        text_sunset_value.text = DateTimeConverter.getHourOfDayFromDateTimeSeconds(weatherData.sunset)

        loadImageAsset(weatherData)
    }

    // A quick and dirty glide call because I'm running out of time
    private fun loadImageAsset(weatherData: CurrentWeatherData){
        Glide.with(this).load("https://openweathermap.org/img/wn/${weatherData.weather[0].icon}@2x.png").into(image_conditionIcon)
    }

    override fun showInfoText(resourceId: Int) {
        text_fetching.text = getString(resourceId)
        text_fetching.visibility = View.VISIBLE
    }

    override fun hideInfoText() {
        text_fetching.visibility = View.INVISIBLE
    }

    override fun showNetworkDialogue() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.main_dialogue_networkTitle))
            .setMessage(getString(R.string.main_dialogue_networkBody))
            .setPositiveButton("OK", null)
            .show()
    }
}