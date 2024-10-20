package com.get_ready.data.remote.models

import com.google.gson.annotations.SerializedName

data class DailyForecast(
    @SerializedName("date_epoch") val timestamp: Long, // Date of the forecast in sec
    @SerializedName("day") val weather: DayWeather
)
