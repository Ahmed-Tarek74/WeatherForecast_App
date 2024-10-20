package com.get_ready.domain.usecases

import com.get_ready.domain.base.BaseTest
import com.get_ready.domain.models.City
import com.get_ready.domain.repo.SearchCityRepository
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class SearchCityUseCaseTest : BaseTest() {

    @Mock
    private lateinit var searchCityRepository: SearchCityRepository

    private lateinit var searchCityUseCase: SearchCityUseCase

    @Before
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.openMocks(this)
        searchCityUseCase = SearchCityUseCase(searchCityRepository)
    }
    @Test
    fun `invoke should return list of cities when repository returns valid data`() = runTest {
        // Arrange
        val cityName = "New York"
        val expectedCities = listOf(
            City(cityName = "New York", country = "USA"),
            City(cityName = "New York City", country = "USA")
        )
        `when`(searchCityRepository.searchCity(cityName)).thenReturn(expectedCities)

        // Act
        val result = searchCityUseCase(cityName)

        // Assert
        assertEquals(expectedCities, result)
        verify(searchCityRepository, times(1)).searchCity(cityName)
        verifyNoMoreInteractions(searchCityRepository)
    }

    @Test
    fun `invoke should throw an exception when repository fails`() = runTest {
        // Arrange
        val cityName = "InvalidCity"
        val exceptionMessage = "Failed to search city"
        `when`(searchCityRepository.searchCity(cityName)).thenThrow(
            RuntimeException(exceptionMessage)
        )

        // Act
        val exception = assertFailsWith<RuntimeException> {
            searchCityUseCase(cityName)
        }

        // Assert
        assertEquals(exceptionMessage, exception.message)
    }
}
