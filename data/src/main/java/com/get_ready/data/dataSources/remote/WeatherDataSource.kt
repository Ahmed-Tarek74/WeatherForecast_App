package com.get_ready.data.dataSources.remote

import com.get_ready.data.remote.models.DailyForecastResponse
import com.get_ready.data.remote.models.WeatherResponse


interface WeatherDataSource {
    suspend fun getCurrentWeather(city: String): WeatherResponse
    suspend fun getDailyForecast(city: String,numOfDays:Int): DailyForecastResponse
}
