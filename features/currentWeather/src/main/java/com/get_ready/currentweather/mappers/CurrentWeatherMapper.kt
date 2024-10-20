package com.get_ready.currentweather.mappers

import com.core.weather_utils.WeatherFormatter.formatHumidity
import com.core.weather_utils.WeatherFormatter.formatPressure
import com.core.weather_utils.WeatherFormatter.formatTemperature
import com.core.weather_utils.WeatherFormatter.formatWindSpeed

import com.get_ready.domain.models.Weather
import com.get_ready.currentweather.models.WeatherUIModel
import com.get_ready.core.ui.utils.DateFormatter

fun Weather.toWeatherUIModel(): WeatherUIModel {

    return WeatherUIModel(
        cityName = cityName,
        date = DateFormatter.formatDate(timestamp),
        temperature = formatTemperature(temperature),
        pressure = formatPressure(pressure),
        humidity = formatHumidity(humidity),
        windSpeed = formatWindSpeed(windSpeed),
        condition = condition,
        iconUrl = iconUrl
    )
}