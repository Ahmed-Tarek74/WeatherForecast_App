package com.get_ready.data.remote.models

import com.google.gson.annotations.SerializedName

data class DailyForecastResponse(
    @SerializedName("forecast") val dailyForecastResponse: DailyForecasts
)
