package com.get_ready.data.remote.models

import com.google.gson.annotations.SerializedName

data class WeatherInfo(
    @SerializedName("condition") val condition: WeatherCondition,
    @SerializedName("last_updated_epoch") val timestamp: Long, //Local time when the real time data was updated in unix time.
    @SerializedName("wind_kph") val windSpeed: Double, //Wind speed in kilometer per hour
    @SerializedName("temp_c") val temperature: Double, // Temperature in celsius
    @SerializedName("pressure_mb") val pressure: Double, // Pressure
    @SerializedName("humidity") val humidity: Int,// Humidity level
)