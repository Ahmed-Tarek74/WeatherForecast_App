package com.get_ready.data.local

import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {
    const val DATASTORE_NAME = "city_prefs"
    val CITY_NAME = stringPreferencesKey("city_name")
}