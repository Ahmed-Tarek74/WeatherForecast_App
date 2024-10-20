package com.get_ready.data.remote.models

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("current") val currentWeather: WeatherInfo,
    @SerializedName("location") val currentLocation: Location,
)
