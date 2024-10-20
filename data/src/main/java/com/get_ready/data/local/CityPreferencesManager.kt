package com.get_ready.data.local

interface CityPreferencesManager {
    suspend fun saveLastSearchedCity(cityName:String)
    suspend fun getLastSearchedCity(): String?
    suspend fun clearLastSearchedCity()
}