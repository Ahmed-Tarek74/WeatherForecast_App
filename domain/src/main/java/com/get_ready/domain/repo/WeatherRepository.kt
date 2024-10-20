package com.get_ready.domain.repo

import com.get_ready.domain.models.Forecast
import com.get_ready.domain.models.Weather

interface WeatherRepository {
    suspend fun getCurrentWeather(city: String): Weather
    suspend fun getDailyForecast(city: String,numOfDays: Int): List<Forecast>
}