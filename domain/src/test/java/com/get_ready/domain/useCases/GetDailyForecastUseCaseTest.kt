package com.get_ready.domain.useCases

import com.get_ready.domain.base.BaseTest
import com.get_ready.domain.models.Forecast
import com.get_ready.domain.repo.WeatherRepository
import com.get_ready.domain.usecases.GetDailyForecastUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import kotlin.test.Test
import kotlin.test.assertFailsWith

class GetDailyForecastUseCaseTest : BaseTest() {

    @Mock
    private lateinit var weatherRepository: WeatherRepository

    private lateinit var getDailyForecastUseCase: GetDailyForecastUseCase

    @Before
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.openMocks(this)
        getDailyForecastUseCase = GetDailyForecastUseCase(weatherRepository)
    }

    @Test
    fun `invoke should return forecast data when repository returns valid data`() =
        runTest{
            // Arrange
            val cityName = "New York"
            val expectedForecasts = listOf(
                Forecast(
                    timestamp = 1234567890L,
                    temperature = 22.0,
                    condition = "Sunny",
                    iconUrl = "http://example.com/icon_sunny.png"
                ),
                Forecast(
                    timestamp = 1234567891L,
                    temperature = 18.0,
                    condition = "Cloudy",
                    iconUrl = "http://example.com/icon_cloudy.png"
                )
            )
            `when`(weatherRepository.getDailyForecast(cityName, 7)).thenReturn(expectedForecasts)

            // Act
            val result = getDailyForecastUseCase(cityName)

            // Assert
            assertEquals(expectedForecasts, result)
            verify(weatherRepository, times(1)).getDailyForecast(cityName, 7)
            verifyNoMoreInteractions(weatherRepository)
        }

    @Test
    fun `invoke should throw an exception when repository fails`() = runTest{
        // Arrange
        val cityName = "InvalidCity"
        val exceptionMessage = "Failed to fetch daily forecast"
        `when`(weatherRepository.getDailyForecast(cityName, 7)).thenThrow(
            RuntimeException(exceptionMessage)
        )

        // Act
        val exception = assertFailsWith<RuntimeException> {
            getDailyForecastUseCase(cityName)
        }

        // Assert
        assertEquals(exceptionMessage, exception.message)
    }
}
