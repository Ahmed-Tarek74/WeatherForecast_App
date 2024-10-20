package com.get_ready.domain.usecases

import com.get_ready.domain.models.City
import com.get_ready.domain.repo.SearchCityRepository
import javax.inject.Inject

class SearchCityUseCase @Inject constructor(private val searchCityRepository: SearchCityRepository) {
    suspend operator fun invoke(cityName:String): List<City> = searchCityRepository.searchCity(cityName)
}