package com.get_ready.data.dataSources.remote

import com.get_ready.data.remote.api.CityApiService
import com.get_ready.data.remote.models.Location
import javax.inject.Inject

class SearchCityRemoteDataSourceImpl @Inject constructor(private val apiService: CityApiService) :
    SearchCityRemoteDataSource {
    override suspend fun searchCity(cityName: String): List<Location> =
        apiService.searchCityByName(cityName)
}