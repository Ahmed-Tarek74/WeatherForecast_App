package com.get_ready.cityinput.mapper

import com.get_ready.domain.models.City
import com.get_ready.cityinput.model.CityUIModel

fun List<City>.toUICities(): List<CityUIModel> {
    val cities = this.map { city -> city.toUICity() }
    return cities
}

fun City.toUICity() = CityUIModel(cityName = cityName, country = country)