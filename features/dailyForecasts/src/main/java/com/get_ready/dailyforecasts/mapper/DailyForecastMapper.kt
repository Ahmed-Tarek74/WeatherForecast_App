package com.get_ready.dailyforecasts.mapper


import com.core.weather_utils.WeatherFormatter.formatTemperature
import com.get_ready.core.ui.utils.DateFormatter
import com.get_ready.domain.models.Forecast
import com.get_ready.dailyforecasts.models.DailyForecastUIModel

private fun Forecast.toDailyForecastUiModel(): DailyForecastUIModel {
    return DailyForecastUIModel(
        date = DateFormatter.formatDate(timestamp),
        temperature = formatTemperature(temperature),
        weatherCondition = condition,
        iconUrl = iconUrl
    )
}

fun List<Forecast>.toDailyForecastsUIModel(): List<DailyForecastUIModel> {
    val dailyForecasts = this.map { dailyForecast ->
        dailyForecast.toDailyForecastUiModel()
    }
    return dailyForecasts
}