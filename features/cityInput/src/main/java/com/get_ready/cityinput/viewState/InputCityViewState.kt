package com.get_ready.cityinput.viewState

import com.get_ready.cityinput.model.CityUIModel

data class InputCityViewState(
    val suggestedCities: List<CityUIModel> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val errorMsg: String? = null,
    val noSuggestionsErrorMsg: String? = null,
)