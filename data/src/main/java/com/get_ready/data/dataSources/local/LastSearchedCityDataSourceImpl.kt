package com.get_ready.data.dataSources.local

import com.get_ready.data.local.CityPreferencesManager
import javax.inject.Inject

class LastSearchedCityDataSourceImpl @Inject constructor(private val cityPreferencesManager: CityPreferencesManager) :
    LastSearchedCityDataSource {
    override suspend fun saveLastSearchedCity(cityName: String) = cityPreferencesManager.saveLastSearchedCity(cityName)

    override suspend fun getLastSearchedCity(): String? = cityPreferencesManager.getLastSearchedCity()

    override suspend fun clearLastSearchedCity() = cityPreferencesManager.clearLastSearchedCity()

}