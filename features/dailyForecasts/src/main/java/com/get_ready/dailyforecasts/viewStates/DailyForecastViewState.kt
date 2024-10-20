package com.get_ready.dailyforecasts.viewStates

import com.get_ready.dailyforecasts.models.DailyForecastUIModel

sealed class DailyForecastViewState {
    data object Loading : DailyForecastViewState()
    data class Error(val errorMsg: String) : DailyForecastViewState()
    data class Success(val dailyForecasts: List<DailyForecastUIModel>) : DailyForecastViewState()
}