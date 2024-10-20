package com.get_ready.domain.usecases

import com.get_ready.domain.repo.LastSearchedCityRepository
import javax.inject.Inject

class GetLastSearchedCityUseCase @Inject constructor(private val cityRepository: LastSearchedCityRepository) {
    suspend operator fun invoke(): String? = cityRepository.getLastSearchedCity()
}