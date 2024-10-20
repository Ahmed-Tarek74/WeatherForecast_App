package com.get_ready.domain.repo


interface LastSearchedCityRepository {
    suspend fun saveLastSearchedCity(cityName:String)
    suspend fun getLastSearchedCity():String?
    suspend fun clearLastSearchedCity()
}