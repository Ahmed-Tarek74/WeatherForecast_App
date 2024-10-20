package com.get_ready.data.mappers

import com.get_ready.data.remote.models.Location
import com.get_ready.domain.models.City

fun List<Location>.toCities():List<City>{
    val cities=this.map { location -> location.toCity() }
    return cities
}
fun Location.toCity(): City = City(cityName = cityName, country = country)