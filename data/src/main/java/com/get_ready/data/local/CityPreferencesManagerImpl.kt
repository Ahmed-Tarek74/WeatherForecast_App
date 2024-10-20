package com.get_ready.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.get_ready.data.local.Constants.CITY_NAME
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class CityPreferencesManagerImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :CityPreferencesManager {
    override suspend fun saveLastSearchedCity(cityName: String) {
        dataStore.edit { preferences ->
            preferences[CITY_NAME]=cityName
        }
    }
    override suspend fun getLastSearchedCity(): String? {
        val preferences = dataStore.data.first()
        return preferences[CITY_NAME]
    }

    override suspend fun clearLastSearchedCity() {
        dataStore.edit{ preferences ->
            preferences.remove(CITY_NAME)
        }
    }
}