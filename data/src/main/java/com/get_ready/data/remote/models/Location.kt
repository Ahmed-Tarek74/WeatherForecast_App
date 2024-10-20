package com.get_ready.data.remote.models

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("name") val cityName: String,
    val country: String
)