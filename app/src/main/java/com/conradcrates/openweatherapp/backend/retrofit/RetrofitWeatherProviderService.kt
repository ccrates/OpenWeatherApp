package com.conradcrates.openweatherapp.backend.retrofit

import com.conradcrates.openweatherapp.backend.BackendTask
import com.conradcrates.openweatherapp.backend.WeatherProviderService
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

    override fun fetchWeatherData(): BackendTask<WeatherData> {
        val lot = "51.503471"
        val long = "-0.119420"
        val apiKey = "9d6b31614315262059403159b7c4d3b6"
        val exclude = "minutely,hourly,daily,alerts"
        val call = service.fetchWeatherData(lot, long, apiKey, exclude)

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