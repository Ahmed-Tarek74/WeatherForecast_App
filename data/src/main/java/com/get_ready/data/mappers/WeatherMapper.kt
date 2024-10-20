package com.get_ready.data.mappers

import com.get_ready.data.remote.constatnts.Constants.ICON_BASE
import com.get_ready.data.remote.models.WeatherCondition
import com.get_ready.data.remote.models.WeatherResponse
import com.get_ready.domain.models.Weather

fun WeatherResponse.toWeather(): Weather {
    return Weather(
        cityName = currentLocation.cityName,
        timestamp = currentWeather.timestamp,
        temperature = currentWeather.temperature,
        pressure = currentWeather.pressure,
        humidity = currentWeather.humidity,
        windSpeed = currentWeather.windSpeed,
        condition = currentWeather.condition.description,
        iconUrl = currentWeather.condition.getIconUrl()
    )
}

fun WeatherCondition.getIconUrl(): String {
    return ICON_BASE + this.icon
}