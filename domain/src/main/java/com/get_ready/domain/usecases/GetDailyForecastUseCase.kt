package com.get_ready.domain.usecases

import com.get_ready.domain.constants.Constants.DEFAULT_FORECAST_DAYS
import com.get_ready.domain.models.Forecast
import com.get_ready.domain.repo.WeatherRepository
import javax.inject.Inject

class GetDailyForecastUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend operator fun invoke(
        cityName: String,
        numOfDays: Int = DEFAULT_FORECAST_DAYS,
    ): List<Forecast> =
        repository.getDailyForecast(cityName, numOfDays)
}