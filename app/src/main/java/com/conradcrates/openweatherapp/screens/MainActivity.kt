package com.conradcrates.openweatherapp.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.conradcrates.openweatherapp.R
import com.conradcrates.openweatherapp.backend.WeatherProvider
import com.conradcrates.openweatherapp.backend.retrofit.RetrofitWeatherProviderService
import com.conradcrates.openweatherapp.models.CurrentWeatherData
import com.conradcrates.openweatherapp.utils.DateTimeConverter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityPresenter.View {

    // TODO Dependency injection here to be improved
    private val presenter = MainActivityPresenter(this, WeatherProvider(RetrofitWeatherProviderService()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        presenter.setup()
    }

    override fun showWeatherData(weatherData: CurrentWeatherData) {
        text_condition_value.text = weatherData.weather[0].description
        text_temperature_value.text = weatherData.temp.toString()
        text_windspeed_value.text = weatherData.wind_speed.toString()
        text_sunset_value.text = DateTimeConverter.getHourOfDayFromDateTimeSeconds(weatherData.sunset)
    }

    override fun showProgressSpinner() {
    }
}