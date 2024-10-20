package com.get_ready.data.remote.api

import com.get_ready.data.remote.constatnts.Constants.CURRENT_WEATHER_ENDPOINT
import com.get_ready.data.remote.constatnts.Constants.DAILY_FORECAST_ENDPOINT
import com.get_ready.data.remote.models.DailyForecastResponse
import com.get_ready.data.remote.models.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {


    @GET(CURRENT_WEATHER_ENDPOINT)
    suspend fun getCurrentWeather(@Query("q") city: String): WeatherResponse

    @GET(DAILY_FORECAST_ENDPOINT)
    suspend fun getDailyForecast(
        @Query("q") city: String,
        @Query("days") numOfDays: Int
    ): DailyForecastResponse


}