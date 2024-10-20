package com.get_ready.data.mappers

import com.get_ready.data.remote.models.*
import com.get_ready.domain.models.Weather
import org.junit.Assert.assertEquals
import org.junit.Test

class WeatherMapperTest {

    @Test
    fun `toWeather should map WeatherResponse to Weather correctly`() {
        // Given
        val weatherResponse = WeatherResponse(
            currentWeather = WeatherInfo(
                condition = WeatherCondition(
                    description = "Sunny",
                    icon = "sunny_icon.png"
                ),
                timestamp = 1632489600,
                windSpeed = 15.5,
                temperature = 25.0,
                pressure = 1013.0,
                humidity = 60
            ),
            currentLocation = Location(
                cityName = "London",
                country = "UK"
            )
        )

        // When
        val weather: Weather = weatherResponse.toWeather()

        // Then
        assertEquals("London", weather.cityName)
        assertEquals(1632489600, weather.timestamp)
        assertEquals(25.0, weather.temperature, 0.01)
        assertEquals(1013.0, weather.pressure, 0.01)
        assertEquals(60, weather.humidity)
        assertEquals(15.5, weather.windSpeed, 0.01)
        assertEquals("Sunny", weather.condition)
        assertEquals("https://sunny_icon.png", weather.iconUrl) // Ensure to use the correct base URL in the actual test
    }

    @Test
    fun `getIconUrl should return correct icon URL`() {
        // Given
        val weatherCondition = WeatherCondition(
            description = "Sunny",
            icon = "sunny_icon.png"
        )

        // When
        val iconUrl: String = weatherCondition.getIconUrl()

        // Then
        assertEquals("https://sunny_icon.png", iconUrl) // Adjust this based on your ICON_BASE constant
    }
}
