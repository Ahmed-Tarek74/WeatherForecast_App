package com.get_ready.data.dataSources.remote

import com.get_ready.data.remote.models.Location

interface SearchCityRemoteDataSource {
    suspend fun searchCity(cityName: String): List<Location>
}