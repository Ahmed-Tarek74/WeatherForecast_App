package com.get_ready.dailyforecasts.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.get_ready.core.ui.composables.WeatherConditionIcon
import com.get_ready.features.R
import com.get_ready.dailyforecasts.models.DailyForecastUIModel
import com.get_ready.core.ui.theme.Dimensions.BORDER_WIDTH
import com.get_ready.core.ui.theme.Dimensions.CARD_CORNER_RADIUS
import com.get_ready.core.ui.theme.Dimensions.INNER_CARD_ELEVATION
import com.get_ready.core.ui.theme.Dimensions.LOW_PADDING
import com.get_ready.core.ui.theme.Dimensions.MEDIUM_PADDING
import com.get_ready.core.ui.theme.Dimensions.SPACER_HEIGHT
import com.get_ready.core.ui.theme.Dimensions.WEATHER_CONDITION_ICO_SIZE
import com.get_ready.core.ui.theme.light_gray


@Composable
fun DailyForecastList(dailyForecasts: List<DailyForecastUIModel>, modifier: Modifier = Modifier) {
    LazyColumn(modifier) {
        items(dailyForecasts) { dailyForecast ->
            DailyForecastCard(dailyForecast)
        }
    }
}
@Composable
fun DailyForecastCard(dailyForecast: DailyForecastUIModel) {
    val textColor = colorScheme.onSecondaryContainer
    val backgroundColor = colorScheme.secondaryContainer
    Card(
        modifier = Modifier
            .padding(LOW_PADDING)
            .fillMaxSize()
            .wrapContentHeight(align = Alignment.Top),
        elevation = CardDefaults.cardElevation(INNER_CARD_ELEVATION),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        shape = RoundedCornerShape(CARD_CORNER_RADIUS),
        border = BorderStroke(
            width = BORDER_WIDTH,
            color = colorScheme.light_gray
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(SPACER_HEIGHT),
            modifier = Modifier
                .padding(MEDIUM_PADDING)
                .fillMaxWidth()
        ) {
            Text(
                text = dailyForecast.date,
                style = MaterialTheme.typography.bodyLarge.copy(color = textColor)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()

            ) {
                WeatherConditionIcon(
                    iconUrl = dailyForecast.iconUrl,
                    iconDescription = stringResource(R.string.icon, dailyForecast.weatherCondition),
                    iconSize = WEATHER_CONDITION_ICO_SIZE
                )
                Text(
                    dailyForecast.temperature,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = textColor,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    dailyForecast.weatherCondition,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = textColor,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}