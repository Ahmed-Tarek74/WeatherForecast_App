package com.get_ready.data.repos

import com.get_ready.data.customExceptions.UnknownException
import com.get_ready.data.dataSources.local.LastSearchedCityDataSource
import com.get_ready.domain.repo.LastSearchedCityRepository
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class LastSearchedCityRepoImplTest {

    @Mock
    private lateinit var dataSource: LastSearchedCityDataSource

    private lateinit var lastSearchedCityRepo: LastSearchedCityRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        lastSearchedCityRepo = LastSearchedCityRepoImpl(dataSource)
    }

    @Test
    fun `saveLastSearchedCity should succeed`() = runTest {
        val cityName = "Cairo"
        // No exception thrown for successful operation
        lastSearchedCityRepo.saveLastSearchedCity(cityName)
        verify(dataSource, times(1)).saveLastSearchedCity(cityName)
    }

    @Test
    fun `saveLastSearchedCity should throw UnknownException on generic exception`() = runTest {
        val cityName = "London"

        // Mock a generic exception when calling saveLastSearchedCity
        `when`(dataSource.saveLastSearchedCity(cityName)).thenThrow(RuntimeException::class.java)

        val exception = assertFailsWith<UnknownException> {
            lastSearchedCityRepo.saveLastSearchedCity(cityName)
        }
        assertEquals("Unexpected error while saving last searched city", exception.message)
    }

    @Test
    fun `getLastSearchedCity should return city name`() = runTest {
        val cityName = "Alexandria"

        // Mock the data source returning a valid city name
        `when`(dataSource.getLastSearchedCity()).thenReturn(cityName)

        val result = lastSearchedCityRepo.getLastSearchedCity()

        assertEquals(cityName, result)
    }

    @Test
    fun `clearLastSearchedCity should succeed`() = runTest {
        // No exception thrown for successful operation
        lastSearchedCityRepo.clearLastSearchedCity()

        verify(dataSource).clearLastSearchedCity()
    }

    @Test
    fun `clearLastSearchedCity should throw UnknownException on generic exception`() = runTest {
        // Mock generic exception when calling clearLastSearchedCity
        `when`(dataSource.clearLastSearchedCity()).thenThrow(RuntimeException::class.java)

        val exception = assertFailsWith<UnknownException> {
            lastSearchedCityRepo.clearLastSearchedCity()
        }
        assertEquals("Unexpected error while clearing last searched city", exception.message)
    }
}