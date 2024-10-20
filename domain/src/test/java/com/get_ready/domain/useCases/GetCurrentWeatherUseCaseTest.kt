package com.get_ready.domain.useCases

import com.get_ready.domain.base.BaseTest
import com.get_ready.domain.models.Weather
import com.get_ready.domain.repo.WeatherRepository
import com.get_ready.domain.usecases.GetCurrentWeatherUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import kotlin.test.Test
import kotlin.test.assertFailsWith

class GetCurrentWeatherUseCaseTest : BaseTest() {

    @Mock
    private lateinit var weatherRepository: WeatherRepository

    private lateinit var getCurrentWeatherUseCase: GetCurrentWeatherUseCase

    @Before
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.openMocks(this)
        getCurrentWeatherUseCase = GetCurrentWeatherUseCase(weatherRepository)
    }

    @Test
    fun `invoke should return weather data when repository returns valid data`() =
        runTest {
            // Arrange
            val cityName = "London"
            val expectedWeather = Weather(
                cityName = "London",
                timestamp = 1234567890L,
                temperature = 20.0,
                pressure = 1013.0,
                humidity = 80,
                windSpeed = 5.0,
                condition = "Clear",
                iconUrl = "http://example.com/icon.png"
            )
            `when`(weatherRepository.getCurrentWeather(cityName)).thenReturn(expectedWeather)

            // Act
            val result = getCurrentWeatherUseCase(cityName)

            // Assert
            assertEquals(expectedWeather, result)
            verify(weatherRepository, times(1)).getCurrentWeather(cityName)
            verifyNoMoreInteractions(weatherRepository)
        }

    @Test
    fun `invoke should throw an exception when repository fails`() = runTest {
        // Arrange
        val cityName = "InvalidCity"
        val exceptionMessage = "Failed to fetch current weather"
        `when`(weatherRepository.getCurrentWeather(cityName)).thenThrow(
            RuntimeException(
                exceptionMessage
            )
        )
        // Act
        val exception = assertFailsWith<RuntimeException> {
            getCurrentWeatherUseCase(cityName)
        }
        assertEquals(exceptionMessage, exception.message)
    }
}