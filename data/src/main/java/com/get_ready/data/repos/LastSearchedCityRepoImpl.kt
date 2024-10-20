package com.get_ready.data.repos

import androidx.datastore.core.IOException
import com.get_ready.data.dataSources.local.LastSearchedCityDataSource
import com.get_ready.data.customExceptions.StorageException
import com.get_ready.data.customExceptions.UnknownException
import com.get_ready.domain.repo.LastSearchedCityRepository
import javax.inject.Inject

class LastSearchedCityRepoImpl @Inject constructor(
    private val dataSource: LastSearchedCityDataSource,
) : LastSearchedCityRepository {

    override suspend fun saveLastSearchedCity(cityName: String) = try {
        dataSource.saveLastSearchedCity(cityName)

    } catch (exception: IOException) {
        throw StorageException("Failed to retrieve city due to storage error")
    } catch (exception: Exception) {
        throw UnknownException("Unexpected error while saving last searched city")
    }

    override suspend fun getLastSearchedCity(): String? = try {
        dataSource.getLastSearchedCity()
    } catch (exception: IOException) {
        throw StorageException("Failed to retrieve city due to storage error")
    } catch (exception: Exception) {
        throw UnknownException("Unexpected error while getting last searched city")
    }


    override suspend fun clearLastSearchedCity() = try {
        dataSource.clearLastSearchedCity()
    } catch (exception: IOException) {
        throw StorageException("Failed to clear saved city name due to storage error")
    } catch (exception: Exception) {
        throw UnknownException("Unexpected error while clearing last searched city")
    }

}