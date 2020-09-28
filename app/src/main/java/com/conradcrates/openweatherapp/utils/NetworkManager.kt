package com.conradcrates.openweatherapp.utils

import android.content.Context
import android.net.ConnectivityManager

class NetworkManager(private val context: Context) {

    fun isNetworkAvailable(): Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}