package com.get_ready.domain.useCases

import com.get_ready.domain.base.BaseTest
import com.get_ready.domain.repo.LastSearchedCityRepository
import com.get_ready.domain.usecases.GetLastSearchedCityUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import kotlin.test.Test
import kotlin.test.assertFailsWith

class GetLastSearchedCityUseCaseTest : BaseTest() {

    @Mock
    private lateinit var cityRepository: LastSearchedCityRepository

    private lateinit var getLastSearchedCityUseCase: GetLastSearchedCityUseCase

    @Before
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.openMocks(this)
        getLastSearchedCityUseCase = GetLastSearchedCityUseCase(cityRepository)
    }

    @Test
    fun `invoke should return last searched city when repository returns valid data`() =
        runTest{
            // Arrange
            val expectedCity = "Paris"
            `when`(cityRepository.getLastSearchedCity()).thenReturn(expectedCity)

            // Act
            val result = getLastSearchedCityUseCase()

            // Assert
            assertEquals(expectedCity, result)
            verify(cityRepository, times(1)).getLastSearchedCity()
            verifyNoMoreInteractions(cityRepository)
        }

    @Test
    fun `invoke should return null when repository returns no last searched city`() =
        runTest{
            // Arrange
            `when`(cityRepository.getLastSearchedCity()).thenReturn(null)

            // Act
            val result = getLastSearchedCityUseCase()

            // Assert
            assertEquals(null, result)
            verify(cityRepository, times(1)).getLastSearchedCity()
            verifyNoMoreInteractions(cityRepository)
        }

    @Test
    fun `invoke should throw an exception when repository fails`() = runTest{
        // Arrange
        val exceptionMessage = "Failed to fetch last searched city"
        `when`(cityRepository.getLastSearchedCity()).thenThrow(
            RuntimeException(exceptionMessage)
        )

        // Act
        val exception = assertFailsWith<RuntimeException> {
            getLastSearchedCityUseCase()
        }

        // Assert
        assertEquals(exceptionMessage, exception.message)
    }
}
