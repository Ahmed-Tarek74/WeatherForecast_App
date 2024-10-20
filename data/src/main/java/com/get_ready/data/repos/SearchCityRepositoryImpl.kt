package com.get_ready.data.repos

import com.get_ready.data.dataSources.remote.SearchCityRemoteDataSource
import com.get_ready.data.mappers.toCities
import com.get_ready.data.network.NetworkHandler
import com.get_ready.domain.models.City
import com.get_ready.domain.repo.SearchCityRepository
import javax.inject.Inject

class SearchCityRepositoryImpl @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val dataSource: SearchCityRemoteDataSource,
) :
    SearchCityRepository {
    override suspend fun searchCity(cityName: String): List<City> = networkHandler.handleNetworkCall {
        dataSource.searchCity(cityName).toCities()
    }
}