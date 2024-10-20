package com.get_ready.data.mappers

import com.get_ready.data.remote.models.DailyForecast
import com.get_ready.data.remote.models.DailyForecastResponse
import com.get_ready.domain.models.Forecast

fun DailyForecastResponse.toDailyForecasts(): List<Forecast> {
    val dailyForecasts = this.dailyForecastResponse.dailyForecasts.map { dailyForecast ->
        dailyForecast.toForecast()
    }
    return dailyForecasts
}

fun DailyForecast.toForecast(): Forecast {
    return Forecast(
        timestamp = timestamp,
        temperature = weather.temperature,
        condition = weather.weatherCondition.description,
        iconUrl = weather.weatherCondition.getIconUrl()
    )
}
