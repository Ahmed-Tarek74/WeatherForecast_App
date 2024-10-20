package com.get_ready.domain.useCases

import com.get_ready.domain.base.BaseTest
import com.get_ready.domain.repo.LastSearchedCityRepository
import com.get_ready.domain.usecases.SaveLastSearchedCityUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import kotlin.test.Test
import kotlin.test.assertFailsWith

class SaveLastSearchedCityUseCaseTest : BaseTest() {

    @Mock
    private lateinit var cityRepository: LastSearchedCityRepository

    private lateinit var saveLastSearchedCityUseCase: SaveLastSearchedCityUseCase

    @Before
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.openMocks(this)
        saveLastSearchedCityUseCase = SaveLastSearchedCityUseCase(cityRepository)
    }

    @Test
    fun `invoke should call saveLastSearchedCity on repository`() = runTest{
        // Arrange
        val cityName = "Tokyo"

        // Act
        saveLastSearchedCityUseCase(cityName)

        // Assert
        verify(cityRepository, times(1)).saveLastSearchedCity(cityName)
        verifyNoMoreInteractions(cityRepository)
    }

    @Test
    fun `invoke should throw an exception when repository fails`() = runTest{
        // Arrange
        val cityName = "InvalidCity"
        val exceptionMessage = "Failed to save last searched city"
        `when`(cityRepository.saveLastSearchedCity(cityName)).thenThrow(
            RuntimeException(exceptionMessage)
        )

        // Act & Assert
        val exception = assertFailsWith<RuntimeException> {
            saveLastSearchedCityUseCase(cityName)
        }
        assertEquals(exceptionMessage, exception.message)
    }
}
