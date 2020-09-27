package com.conradcrates.openweatherapp

import com.conradcrates.openweatherapp.utils.DateTimeConverter
import org.junit.Assert
import org.junit.Test

class DateTimeConverterTests {

    private val SEPT_27_2020_2PM_IN_SECONDS = 1601211615L

    // GIVEN getHourOfDayFromDateTimeSeconds has been called
    // WHEN a dateTime value is given
    // THEN return the hour of day for that dateTime
    @Test
    fun getHourOfDayFromDateTimeSeconds_dateTimeProvider_returnHourOfDay(){
        val result = DateTimeConverter.getHourOfDayFromDateTimeSeconds(SEPT_27_2020_2PM_IN_SECONDS)

        Assert.assertEquals("14:00", result)
    }
}