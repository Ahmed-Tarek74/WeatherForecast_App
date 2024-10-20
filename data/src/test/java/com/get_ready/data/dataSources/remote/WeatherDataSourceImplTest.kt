package com.get_ready.data.dataSources.remote

import com.get_ready.data.remote.api.WeatherApiService
import com.get_ready.data.remote.models.DailyForecastResponse
import com.get_ready.data.remote.models.DailyForecasts
import com.get_ready.data.remote.models.Location
import com.get_ready.data.remote.models.WeatherCondition
import com.get_ready.data.remote.models.WeatherInfo
import com.get_ready.data.remote.models.WeatherResponse
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.times
import kotlin.test.Test
import kotlin.test.assertFailsWith

class WeatherDataSourceImplTest {

    @Mock
    private lateinit var weatherApiService: WeatherApiService

    private lateinit var weatherDataSource: WeatherDataSourceImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        weatherDataSource = WeatherDataSourceImpl(weatherApiService)
    }

    @Test
    fun `getCurrentWeather should return weather data successfully`() = runTest {
        //Arrange
        val cityName = "Cairo"
        val expectedWeatherResponse = WeatherResponse(
            currentWeather = WeatherInfo(
                condition = WeatherCondition("clear", ""),
                timestamp = 12345L,
                windSpeed = 12.5,
                temperature = 20.3,
                pressure = 1025.3,
                humidity = 90
            ),
            currentLocation = Location(cityName,"Egypt")
        )
        `when`(weatherApiService.getCurrentWeather(cityName)).thenReturn(expectedWeatherResponse)

        // Act
        val result = weatherDataSource.getCurrentWeather(cityName)

        // Assert
        assertEquals(expectedWeatherResponse, result)
        verify(weatherApiService, times(1)).getCurrentWeather(cityName)
    }

    @Test
    fun `getCurrentWeather should throw an exception when API fails`() = runTest {
        // Given
        val cityName = "New York"
        val exceptionMessage = "Failed to fetch weather data"
        `when`(weatherApiService.getCurrentWeather(cityName)).thenThrow(
            RuntimeException(
                exceptionMessage
            )
        )

        // When & Then
        val exception = assertFailsWith<RuntimeException> {
            weatherDataSource.getCurrentWeather(cityName)
        }
        assertEquals(exceptionMessage, exception.message)
    }

    @Test
    fun `getDailyForecast should return forecast data successfully`() = runTest {
        // Arrange
        val cityName = "New York"
        val numOfDays = 7
        val expectedForecastResponse = DailyForecastResponse(DailyForecasts(listOf())) // Mock response
        `when`(weatherApiService.getDailyForecast(cityName, numOfDays)).thenReturn(
            expectedForecastResponse
        )
        // Act
        val result = weatherDataSource.getDailyForecast(cityName, numOfDays)

        // Assert
        assertEquals(expectedForecastResponse, result)
        verify(weatherApiService).getDailyForecast(cityName, numOfDays)
    }

    @Test
    fun `getDailyForecast should throw an exception when API fails`() = runTest {
        // Arrange
        val cityName = "New York"
        val numOfDays = 7
        val exceptionMessage = "Failed to fetch forecast data"
        `when`(weatherApiService.getDailyForecast(cityName, numOfDays)).thenThrow(
            RuntimeException(
                exceptionMessage
            )
        )

        // ACT AND Assert
        val exception = assertFailsWith<RuntimeException> {
            weatherDataSource.getDailyForecast(cityName, numOfDays)
        }
        assertEquals(exceptionMessage, exception.message)
    }
}
