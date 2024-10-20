package com.get_ready.domain.models

data class Forecast(
    val timestamp: Long,
    val temperature: Double,
    val condition: String,
    val iconUrl: String
)

