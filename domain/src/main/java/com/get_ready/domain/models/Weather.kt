package com.get_ready.domain.models

data class Weather(
    val cityName: String,
    val timestamp:Long,
    val temperature: Double,
    val pressure: Double,
    val humidity: Int,
    val windSpeed :Double,
    val condition: String,
    val iconUrl: String
)

