package com.get_ready.data.remote.models

import com.google.gson.annotations.SerializedName

data class WeatherCondition(
    @SerializedName("text") val description: String, //Weather condition description (e.g., sunny, cloudy)
    @SerializedName("icon") val icon: String // Weather condition icon
)
