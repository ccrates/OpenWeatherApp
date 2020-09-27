package com.conradcrates.openweatherapp.utils

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object DateTimeConverter {

    fun getHourOfDayFromDateTimeSeconds(dateTimeInSeconds: Long): String{
        val date = Date(TimeUnit.SECONDS.toMillis(dateTimeInSeconds))

        return SimpleDateFormat("HH:mm", Locale.UK).format(date)
    }
}