package com.get_ready.cityinput.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.get_ready.cityinput.event.SaveLastSearchedCityEvent
import com.get_ready.cityinput.intent.InputCityIntent
import com.get_ready.cityinput.mapper.toUICities
import com.get_ready.cityinput.viewModel.InputCityViewModel
import com.get_ready.domain.models.City
import com.get_ready.domain.usecases.SaveLastSearchedCityUseCase
import com.get_ready.domain.usecases.SearchCityUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class InputCityViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: InputCityViewModel
    private lateinit var searchCityUseCase: SearchCityUseCase
    private lateinit var saveLastSearchedCityUseCase: SaveLastSearchedCityUseCase

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        searchCityUseCase = mock(SearchCityUseCase::class.java)
        saveLastSearchedCityUseCase = mock(SaveLastSearchedCityUseCase::class.java)
        viewModel = InputCityViewModel(searchCityUseCase, saveLastSearchedCityUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test searchCities with valid query`() = runTest {
        // Given
        val query = "Cair"
        val cities = listOf(
            City("Cairo", "Egypt"),
            City("Cairns", "Australia")
        )
        `when`(searchCityUseCase(query)).thenReturn(cities)

        // When
        viewModel.setIntent(InputCityIntent.SearchQueryChanged(query))
        testDispatcher.scheduler.advanceUntilIdle()

        val loadingState = viewModel.viewState.first { it.isLoading }
        assertTrue(loadingState.isLoading)

        // Assert the final state once loading is complete
        val finalState = viewModel.viewState.first { !it.isLoading }
        assertEquals(cities.size, finalState.suggestedCities.size)
        assertTrue(cities.toUICities().containsAll(finalState.suggestedCities))
    }

    @Test
    fun `test searchCities with empty query`() = runTest {
        // Given
        val query = ""
        // When
        viewModel.setIntent(InputCityIntent.SearchQueryChanged(query))
        testDispatcher.scheduler.advanceUntilIdle()
        // Then
        val state = viewModel.viewState.first()
        assertTrue(state.suggestedCities.isEmpty())
    }

    @Test
    fun `test last searched city saved successfully`() = runTest {
        // Arrange
        val cityName = "Los Angeles"
        `when`(saveLastSearchedCityUseCase.invoke(cityName)).thenReturn(Unit)
        // Act
        viewModel.setIntent(InputCityIntent.CitySelected(cityName))
        testDispatcher.scheduler.advanceUntilIdle()
        // Then
        val event = viewModel.citySaveEvent.first()
        assertTrue(event is SaveLastSearchedCityEvent.Success)
        assertEquals(cityName, (event as SaveLastSearchedCityEvent.Success).savedCity)
        verify(saveLastSearchedCityUseCase).invoke(cityName)
    }

    @Test
    fun `test storing last searched city failure`() = runTest {
        // Arrange
        val cityName = "Los Angeles"
        `when`(saveLastSearchedCityUseCase(cityName)).thenThrow(RuntimeException("Save failed"))
        // Act
        viewModel.setIntent(InputCityIntent.CitySelected(cityName))
        testDispatcher.scheduler.advanceUntilIdle()
        // Assert
        val event = viewModel.citySaveEvent.first()
        assertTrue(event is SaveLastSearchedCityEvent.Failure)
        assertEquals("Save failed", (event as SaveLastSearchedCityEvent.Failure).errorMsg)
    }
}