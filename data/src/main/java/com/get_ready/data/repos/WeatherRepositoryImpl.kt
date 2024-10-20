package com.get_ready.data.repos

import com.get_ready.data.dataSources.remote.WeatherDataSource
import com.get_ready.data.mappers.toDailyForecasts
import com.get_ready.data.mappers.toWeather
import com.get_ready.data.network.NetworkHandler
import com.get_ready.domain.models.Forecast
import com.get_ready.domain.models.Weather
import com.get_ready.domain.repo.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val dataSource: WeatherDataSource,
    private val networkHandler: NetworkHandler
) : WeatherRepository {
    override suspend fun getCurrentWeather(city: String): Weather {
        return networkHandler.handleNetworkCall {
            dataSource.getCurrentWeather(city).toWeather()
        }
    }
    override suspend fun getDailyForecast(city: String, numOfDays: Int): List<Forecast> {
        return networkHandler.handleNetworkCall {
            dataSource.getDailyForecast(city = city, numOfDays).toDailyForecasts()
        }
    }
}