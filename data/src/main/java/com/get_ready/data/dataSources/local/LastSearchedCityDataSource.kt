package com.get_ready.data.dataSources.local

interface LastSearchedCityDataSource {
    suspend fun saveLastSearchedCity(cityName:String)
    suspend fun getLastSearchedCity(): String?
    suspend fun clearLastSearchedCity()
}