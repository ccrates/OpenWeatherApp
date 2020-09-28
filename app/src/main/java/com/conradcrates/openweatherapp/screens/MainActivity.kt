package com.conradcrates.openweatherapp.screens

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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
        text_condition_value.text = weatherData.weather[0].description
        text_temperature_value.text = weatherData.temp.toString()
        text_windspeed_value.text = weatherData.wind_speed.toString()
        text_sunset_value.text = DateTimeConverter.getHourOfDayFromDateTimeSeconds(weatherData.sunset)
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