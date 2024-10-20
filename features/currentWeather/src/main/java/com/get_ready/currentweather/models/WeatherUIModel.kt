package com.get_ready.currentweather.models

data class WeatherUIModel(
    val cityName: String,
    val date: String,
    val temperature: String,
    val pressure: String,
    val humidity: String,
    val windSpeed: String,
    val condition: String,
    val iconUrl: String
)
