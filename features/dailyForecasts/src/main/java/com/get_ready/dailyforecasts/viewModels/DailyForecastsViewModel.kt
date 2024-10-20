package com.get_ready.dailyforecasts.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.get_ready.core.ui.BaseViewModel
import com.get_ready.domain.usecases.GetDailyForecastUseCase
import com.get_ready.features.ScreenRoute
import com.get_ready.dailyforecasts.mapper.toDailyForecastsUIModel
import com.get_ready.dailyforecasts.viewStates.DailyForecastViewState
import com.get_ready.dailyforecasts.viewStates.DailyForecastViewState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DailyForecastsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dailyForecastUseCase: GetDailyForecastUseCase,
) : BaseViewModel() {

    private val _viewState: MutableStateFlow<DailyForecastViewState> =
        MutableStateFlow(Loading)

    val viewState: StateFlow<DailyForecastViewState> get() = _viewState
    private val cityName = savedStateHandle.toRoute<ScreenRoute.DailyForecastScreen>().cityName

    init {
        getDailyForecasts(cityName)
    }

    override fun handleException(exception: Throwable) {
        _viewState.value =
            Error(exception.message ?: "Unexpected error occurred")
    }

    private fun getDailyForecasts(cityName: String) {
        launchOnIODispatcher {
            try {
                _viewState.value = Success(dailyForecastUseCase(cityName).toDailyForecastsUIModel())
            } catch (exception: Exception) {
                _viewState.value = Error(exception.message ?: "Unable to fetch daily forecasts")
            }
        }
    }
}