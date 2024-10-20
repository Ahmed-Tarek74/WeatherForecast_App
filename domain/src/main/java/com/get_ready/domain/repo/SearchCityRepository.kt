package com.get_ready.domain.repo

import com.get_ready.domain.models.City

interface SearchCityRepository {
    suspend fun searchCity(cityName:String):List<City>
}