package com.get_ready.data.remote.models

import com.google.gson.annotations.SerializedName

data class DayWeather(
    @SerializedName("maxtemp_c") val temperature: Double,   // Day's max temperature in Celsius
    @SerializedName("condition") val weatherCondition: WeatherCondition
)
