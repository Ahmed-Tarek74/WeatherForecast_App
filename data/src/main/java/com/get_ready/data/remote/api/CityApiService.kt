package com.get_ready.data.remote.api

import com.get_ready.data.remote.constatnts.Constants.CITY_AUTOCOMPLETE_ENDPOINT
import com.get_ready.data.remote.models.Location
import retrofit2.http.GET
import retrofit2.http.Query

interface CityApiService {
    @GET(CITY_AUTOCOMPLETE_ENDPOINT)
    suspend fun searchCityByName(@Query("q") city: String): List<Location>
}