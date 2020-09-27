package com.conradcrates.openweatherapp.screens

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.conradcrates.openweatherapp.R
import com.conradcrates.openweatherapp.backend.WeatherProvider
import com.conradcrates.openweatherapp.backend.retrofit.RetrofitWeatherProviderService
import com.conradcrates.openweatherapp.models.WeatherData
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

    override fun showWeatherData(weatherData: WeatherData) {
        text_condition_value.text = weatherData.current.weather[0].description
    }

    override fun showProgressSpinner() {
    }
}