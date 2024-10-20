package com.get_ready.data.dataSources.local

import com.get_ready.data.local.CityPreferencesManager
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import kotlin.test.Test

class LastSearchedCityDataSourceImplTest {

    // Mock dependencies
    @Mock
    private lateinit var cityPreferencesManager: CityPreferencesManager

    // Class under test
    private lateinit var lastSearchedCityDataSourceImpl: LastSearchedCityDataSourceImpl

    @Before
    fun setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this)
        // Initialize the data source with the mocked CityPreferencesManager
        lastSearchedCityDataSourceImpl = LastSearchedCityDataSourceImpl(cityPreferencesManager)
    }

    @Test
    fun `saveLastSearchedCity should call CityPreferencesManager saveLastSearchedCity`(): Unit = runBlocking {
        // Arrange
        val cityName = "New York"

        // Act
        lastSearchedCityDataSourceImpl.saveLastSearchedCity(cityName)

        // Assert
        verify(cityPreferencesManager, times(1)).saveLastSearchedCity(cityName)
        verifyNoMoreInteractions(cityPreferencesManager)
    }

    @Test
    fun `getLastSearchedCity should return value from CityPreferencesManager`(): Unit = runBlocking {
        // Arrange
        val expectedCity = "London"
        `when`(cityPreferencesManager.getLastSearchedCity()).thenReturn(expectedCity)

        // Act
        val result = lastSearchedCityDataSourceImpl.getLastSearchedCity()

        // Assert
        assertEquals(expectedCity, result)
        verify(cityPreferencesManager,times(1)).getLastSearchedCity()
        verifyNoMoreInteractions(cityPreferencesManager)
    }

    @Test
    fun `clearLastSearchedCity should call CityPreferencesManager clearLastSearchedCity`() = runBlocking {
        // Act
        lastSearchedCityDataSourceImpl.clearLastSearchedCity()

        // Assert
        verify(cityPreferencesManager,times(1)).clearLastSearchedCity()
        verifyNoMoreInteractions(cityPreferencesManager)
    }
}