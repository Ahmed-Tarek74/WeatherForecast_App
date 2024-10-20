package com.get_ready.data.remote.models

import com.google.gson.annotations.SerializedName

data class DailyForecasts(
    @SerializedName("forecastday") val dailyForecasts: List<DailyForecast>, // List of daily forecasts
)