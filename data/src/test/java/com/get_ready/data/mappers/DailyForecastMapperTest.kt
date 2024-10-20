package com.get_ready.data.mappers

import com.get_ready.data.remote.models.*
import com.get_ready.domain.models.Forecast
import org.junit.Assert.assertEquals
import org.junit.Test

class DailyForecastMapperTest {

    @Test
    fun `toDailyForecasts should map DailyForecastResponse to List of Forecast correctly`() {
        // Given
        val dailyForecastResponse = DailyForecastResponse(
            dailyForecastResponse = DailyForecasts(
                dailyForecasts = listOf(
                    DailyForecast(
                        timestamp = 1632489600, // Example timestamp
                        weather = DayWeather(
                            temperature = 23.5,
                            weatherCondition = WeatherCondition(
                                description = "Sunny",
                                icon = "sunny_icon.png"
                            )
                        )
                    ),
                    DailyForecast(
                        timestamp = 1632576000, // Example timestamp
                        weather = DayWeather(
                            temperature = 19.0,
                            weatherCondition = WeatherCondition(
                                description = "Cloudy",
                                icon = "cloudy_icon.png"
                            )
                        )
                    )
                )
            )
        )

        // When
        val forecastList: List<Forecast> = dailyForecastResponse.toDailyForecasts()

        // Then
        assertEquals(2, forecastList.size)

        // First forecast
        assertEquals(1632489600, forecastList[0].timestamp)
        assertEquals(23.5, forecastList[0].temperature, 0.01)
        assertEquals("Sunny", forecastList[0].condition)
        assertEquals("https://sunny_icon.png", forecastList[0].iconUrl)

        // Second forecast
        assertEquals(1632576000, forecastList[1].timestamp)
        assertEquals(19.0, forecastList[1].temperature, 0.01)
        assertEquals("Cloudy", forecastList[1].condition)
        assertEquals("https://cloudy_icon.png", forecastList[1].iconUrl)
    }

    @Test
    fun `toForecast should map DailyForecast to Forecast correctly`() {
        // Given
        val dailyForecast = DailyForecast(
            timestamp = 1632489600,
            weather = DayWeather(
                temperature = 23.5,
                weatherCondition = WeatherCondition(
                    description = "Sunny",
                    icon = "sunny_icon.png"
                )
            )
        )

        // When
        val forecast: Forecast = dailyForecast.toForecast()

        // Then
        assertEquals(1632489600, forecast.timestamp)
        assertEquals(23.5, forecast.temperature, 0.01)
        assertEquals("Sunny", forecast.condition)
        assertEquals("https://sunny_icon.png", forecast.iconUrl)
    }
}
