package com.get_ready.cityinput.event

sealed class SaveLastSearchedCityEvent {
    data class Success(val savedCity: String) : SaveLastSearchedCityEvent()
    data class Failure(val errorMsg: String) : SaveLastSearchedCityEvent()
}