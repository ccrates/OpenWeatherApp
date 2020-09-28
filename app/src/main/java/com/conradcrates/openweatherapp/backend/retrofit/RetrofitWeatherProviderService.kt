package com.conradcrates.openweatherapp.backend.retrofit

import com.conradcrates.openweatherapp.backend.BackendTask
import com.conradcrates.openweatherapp.backend.WeatherProviderService
import com.conradcrates.openweatherapp.constants.AppConstants
import com.conradcrates.openweatherapp.models.WeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitWeatherProviderService: WeatherProviderService {

    private val baseUrl = "https://api.openweathermap.org/data/2.5/"
    private val service: RetrofitInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(RetrofitInterface::class.java)
    }

    override fun fetchWeatherData(lat: Double, long: Double): BackendTask<WeatherData> {
        val apiKey = AppConstants.API_KEY
        val exclude = "minutely,hourly,daily,alerts"
        val units = "metric"
        val call = service.fetchWeatherData(lat.toString(), long.toString(), apiKey, exclude, units)

        return BackendTask { task ->
            call.enqueue(object : Callback<WeatherData> {
                override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                    response.body()?.let {
                        task.successResult(it)
                    }
                }

                override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                    task.errorResult(null)
                }
            })
        }
    }
}