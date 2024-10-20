package com.get_ready.currentweather.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.get_ready.core.ui.composables.WeatherConditionIcon
import com.get_ready.features.R
import com.get_ready.currentweather.models.WeatherUIModel
import com.get_ready.core.ui.theme.Dimensions.LOW_PADDING
import com.get_ready.core.ui.theme.Dimensions.SPACER_HEIGHT
import com.get_ready.core.ui.theme.Weather_AppTheme


@Composable
fun WeatherDetailsCard(weather: WeatherUIModel) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.secondaryContainer
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(32.dp),
            modifier = Modifier.padding(vertical = 32.dp, horizontal = 8.dp)
        ) {
            Column(modifier = Modifier.padding(start = LOW_PADDING)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()

                ) {
                    WeatherConditionIcon(
                        iconUrl = weather.iconUrl,
                        iconDescription = stringResource(R.string.icon, weather.condition)
                    )
                    Text(
                        weather.temperature,
                        style = MaterialTheme.typography.headlineLarge.copy(
                            color = colorScheme.onSecondaryContainer,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Spacer(modifier = Modifier.height(SPACER_HEIGHT))
                Text(
                    weather.condition,
                    style = MaterialTheme.typography.headlineMedium.copy(color = colorScheme.onSecondaryContainer)
                )
            }
            WeatherFactorsRow(weather = weather)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun WeatherDetailsCardPreview() {
    Weather_AppTheme {
        WeatherDetailsCard(
            weather = WeatherUIModel(
                cityName = "Cairo",
                date = "Thursday,Oct 17",
                temperature = "24°C",
                pressure = "1254 hpa",
                humidity = "80%",
                windSpeed = "1.54 Kph",
                condition = "Sunny",
                iconUrl = ""
            )
        )
    }
}