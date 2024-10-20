package com.get_ready.weather_app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.get_ready.domain.usecases.GetLastSearchedCityUseCase
import com.get_ready.features.ScreenRoute.CityInputScreen
import com.get_ready.features.ScreenRoute.CurrentWeatherScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: MainViewModel
    private lateinit var getLastSearchedCityUseCase: GetLastSearchedCityUseCase

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getLastSearchedCityUseCase = mock(GetLastSearchedCityUseCase::class.java)
        viewModel = MainViewModel(getLastSearchedCityUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should set start destination to CityInputScreen when no saved city`() = runTest {
        // Arrange
        val expectedStartDestination = CityInputScreen
        `when`(getLastSearchedCityUseCase.invoke()).thenReturn(null)

        // Act And Assert

        val viewModel = MainViewModel(getLastSearchedCityUseCase)
        val actualStartDestination = viewModel.startDestination.first()
        assertEquals(expectedStartDestination, actualStartDestination)
    }

    @Test
    fun `should set start destination to CurrentWeatherScreen when a saved city is present`() =
        runTest {
            // Arrange
            val savedCity = "New York"
            val expectedStartDestination = CurrentWeatherScreen(savedCity)
            `when`(getLastSearchedCityUseCase.invoke()).thenReturn(savedCity)
            // Act And Assert
            val viewModel = MainViewModel(getLastSearchedCityUseCase)

            val actualStartDestination = viewModel.startDestination.first()
            assertEquals(expectedStartDestination, actualStartDestination)
        }
    @Test
    fun `should set start destination to CityInputScreen on exception`() = runTest {
        // Arrange
        val expectedStartDestination = CityInputScreen
        `when`(getLastSearchedCityUseCase.invoke()).thenThrow(RuntimeException("UnExpected Error"))

        // Act And Assert
        val viewModel = MainViewModel(getLastSearchedCityUseCase)

        val actualStartDestination = viewModel.startDestination.first()
        assertEquals(expectedStartDestination, actualStartDestination)
    }

}