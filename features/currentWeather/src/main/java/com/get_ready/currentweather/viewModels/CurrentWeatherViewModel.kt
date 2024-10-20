package com.get_ready.currentweather.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.get_ready.core.ui.BaseViewModel
import com.get_ready.domain.usecases.GetCurrentWeatherUseCase
import com.get_ready.features.ScreenRoute
import com.get_ready.currentweather.mappers.toWeatherUIModel
import com.get_ready.currentweather.viewStates.CurrentWeatherViewState
import com.get_ready.currentweather.viewStates.CurrentWeatherViewState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
) : BaseViewModel() {
    private val _viewState: MutableStateFlow<CurrentWeatherViewState> =
        MutableStateFlow(Loading)

    val viewState: StateFlow<CurrentWeatherViewState> get() = _viewState
    private val city = savedStateHandle.toRoute<ScreenRoute.CurrentWeatherScreen>().cityName


    init {
        getCurrentWeather(city)
    }

    override fun handleException(exception: Throwable) {
        _viewState.value = Error(
            exception.message ?: "Unexpected error occurred"
        )
    }

    fun getCurrentWeather(city: String) {
       launchOnIODispatcher {
            try {
                _viewState.value = Success(getCurrentWeatherUseCase(city).toWeatherUIModel())
            } catch (exception: Exception) {
                _viewState.value = Error(exception.message ?: "Unable to fetch weather data")
            }
        }
    }
}