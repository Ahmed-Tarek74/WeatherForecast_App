package com.get_ready.weather_app.viewModel

import com.get_ready.core.ui.BaseViewModel
import com.get_ready.domain.usecases.GetLastSearchedCityUseCase
import com.get_ready.features.ScreenRoute
import com.get_ready.features.ScreenRoute.CityInputScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getLastSearchedCityUseCase: GetLastSearchedCityUseCase) :
    BaseViewModel() {
    private val _startDestination = MutableStateFlow<ScreenRoute>(CityInputScreen)
    val startDestination: StateFlow<ScreenRoute> = _startDestination
    init {
        determineStartDestination()
    }
    private fun determineStartDestination() {
        launchOnIODispatcher {
            try {
                val savedCity = getLastSearchedCityUseCase()
                _startDestination.value = if (savedCity == null) {
                    CityInputScreen
                } else {
                    ScreenRoute.CurrentWeatherScreen(savedCity)
                }
            } catch (e: Exception) {
                _startDestination.value = CityInputScreen
            }
        }
    }
    override fun handleException(exception: Throwable) {
        _startDestination.value = CityInputScreen
    }
}