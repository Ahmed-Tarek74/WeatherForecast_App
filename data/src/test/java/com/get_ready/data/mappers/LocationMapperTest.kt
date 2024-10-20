package com.get_ready.data.mappers

import com.get_ready.data.remote.models.Location
import org.junit.Assert.assertEquals
import org.junit.Test

class LocationMapperTest {

    @Test
    fun `toCity should map Location to City correctly`() {
        // Arrange
        val location = Location(cityName = "London", country = "UK")

        // Act
        val city = location.toCity()

        // Assert
        assertEquals("London", city.cityName)
        assertEquals("UK", city.country)
    }

    @Test
    fun `toCities should map List of Location to List of City correctly`() {
        // Arrange
        val locations = listOf(
            Location(cityName = "London", country = "UK"),
            Location(cityName = "Toronto", country = "Canada"),
            Location(cityName = "Tokyo", country = "Japan")
        )

        // Act
        val cities = locations.toCities()

        // Assert
        assertEquals(3, cities.size)
        assertEquals("London", cities[0].cityName)
        assertEquals("UK", cities[0].country)
        assertEquals("Toronto", cities[1].cityName)
        assertEquals("Canada", cities[1].country)
        assertEquals("Tokyo", cities[2].cityName)
        assertEquals("Japan", cities[2].country)
    }

    @Test
    fun `toCities should return an empty list when input list is empty`() {
        // Arrange
        val locations = emptyList<Location>()

        // Act
        val cities = locations.toCities()

        // Assert
        assertEquals(0, cities.size)
    }
}
