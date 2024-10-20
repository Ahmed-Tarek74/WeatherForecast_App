package com.get_ready.currentweather.viewStates

import com.get_ready.currentweather.models.WeatherUIModel

sealed class CurrentWeatherViewState {
    data object Loading : CurrentWeatherViewState()
    data class Error(val errorMsg: String) : CurrentWeatherViewState()
    data class Success(val weather: WeatherUIModel) : CurrentWeatherViewState()
}