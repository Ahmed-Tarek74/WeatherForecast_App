package com.get_ready.dailyforecasts.models

data class DailyForecastUIModel(
    val date: String,
    val temperature: String,
    val weatherCondition: String,
    val iconUrl: String
)
