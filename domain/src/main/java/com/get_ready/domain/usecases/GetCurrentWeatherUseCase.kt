package com.get_ready.domain.usecases

import com.get_ready.domain.models.Weather
import com.get_ready.domain.repo.WeatherRepository
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend operator fun invoke(city: String): Weather = repository.getCurrentWeather(city)
}