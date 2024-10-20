package com.get_ready.cityinput.intent

sealed class InputCityIntent {
    data class SearchQueryChanged(val searchQuery: String) : InputCityIntent()
    data class CitySelected(val cityName: String) : InputCityIntent()
}