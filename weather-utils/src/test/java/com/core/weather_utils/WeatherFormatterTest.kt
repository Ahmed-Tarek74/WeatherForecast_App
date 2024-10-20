package com.core.weather_utils

import org.junit.Assert.assertEquals
import org.junit.Test

class WeatherFormatterTest {

    @Test
    fun `formatTemperature returns formatted temperature`() {
        val temp = 25.345
        val expected = "25.3°C"
        val result = WeatherFormatter.formatTemperature(temp)
        assertEquals(expected, result)
    }

    @Test
    fun `formatHumidity returns formatted humidity`() {
        val humidity = 80
        val expected = "80%"
        val result = WeatherFormatter.formatHumidity(humidity)
        assertEquals(expected, result)
    }

    @Test
    fun `formatWindSpeed returns formatted wind speed`() {
        val windSpeed = 5.678
        val expected = "5.7 km/h"
        val result = WeatherFormatter.formatWindSpeed(windSpeed)
        assertEquals(expected, result)
    }

    @Test
    fun `formatPressure returns formatted pressure`() {
        val pressure = 1013.456
        val expected = "1013 mb"
        val result = WeatherFormatter.formatPressure(pressure)
        assertEquals(expected, result)
    }
}