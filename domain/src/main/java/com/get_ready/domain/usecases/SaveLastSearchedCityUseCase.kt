package com.get_ready.domain.usecases

import com.get_ready.domain.repo.LastSearchedCityRepository
import javax.inject.Inject

class SaveLastSearchedCityUseCase @Inject constructor(private val cityRepository: LastSearchedCityRepository) {
    suspend operator fun invoke(cityName: String) = cityRepository.saveLastSearchedCity(cityName)
}