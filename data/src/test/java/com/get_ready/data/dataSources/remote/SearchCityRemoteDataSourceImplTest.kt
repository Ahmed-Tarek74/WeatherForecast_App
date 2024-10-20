package com.get_ready.data.dataSources.remote

import com.get_ready.data.remote.api.CityApiService
import com.get_ready.data.remote.models.Location
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import kotlin.test.Test

class SearchCityRemoteDataSourceImplTest {

    @Mock
    private lateinit var apiService: CityApiService

    private lateinit var searchCityRemoteDataSourceImpl: SearchCityRemoteDataSourceImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        searchCityRemoteDataSourceImpl = SearchCityRemoteDataSourceImpl(apiService)
    }

    @Test
    fun `searchCity should return list of Location from apiService`() = runBlocking {
        // Arrange
        val cityName = "Paris"
        val expectedLocations = listOf(
            Location("Paris", "France"),
            Location("Paris", "USA")
        )

        `when`(apiService.searchCityByName(cityName)).thenReturn(expectedLocations)

        // Act
        val result = searchCityRemoteDataSourceImpl.searchCity(cityName)

        // Assert
        assertEquals(expectedLocations, result)
        verify(apiService).searchCityByName(cityName)
        verifyNoMoreInteractions(apiService)
    }
}