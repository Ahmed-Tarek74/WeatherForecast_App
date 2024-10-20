package com.get_ready.data.dataSources.remote

import com.get_ready.data.remote.api.WeatherApiService
import com.get_ready.data.remote.models.DailyForecastResponse
import com.get_ready.data.remote.models.WeatherResponse
import javax.inject.Inject


class WeatherDataSourceImpl @Inject constructor(private val weatherApiService: WeatherApiService) :
    WeatherDataSource {
    override suspend fun getCurrentWeather(city: String): WeatherResponse =
        weatherApiService.getCurrentWeather(city)

    override suspend fun getDailyForecast(city: String,numOfDays:Int): DailyForecastResponse =
        weatherApiService.getDailyForecast(city,numOfDays)
}
