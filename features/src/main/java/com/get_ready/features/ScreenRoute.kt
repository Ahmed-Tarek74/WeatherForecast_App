package com.get_ready.features

import kotlinx.serialization.Serializable

sealed class ScreenRoute {

    @Serializable
    data class CurrentWeatherScreen(val cityName: String) : ScreenRoute()

    @Serializable
    data class DailyForecastScreen(val cityName: String) : ScreenRoute()

    @Serializable
    data object CityInputScreen : ScreenRoute()
}