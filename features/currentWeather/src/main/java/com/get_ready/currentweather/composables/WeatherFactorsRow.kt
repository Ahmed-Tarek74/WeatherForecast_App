package com.get_ready.currentweather.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.get_ready.features.R
import com.get_ready.currentweather.models.WeatherUIModel
import com.get_ready.core.ui.theme.Dimensions.BORDER_WIDTH
import com.get_ready.core.ui.theme.Dimensions.CARD_CORNER_RADIUS
import com.get_ready.core.ui.theme.Dimensions.FACTOR_CARD_PADDING_HORIZONTAL
import com.get_ready.core.ui.theme.Dimensions.FACTOR_ICON_SIZE
import com.get_ready.core.ui.theme.Dimensions.HIGH_PADDING
import com.get_ready.core.ui.theme.Dimensions.INNER_CARD_ELEVATION
import com.get_ready.core.ui.theme.Dimensions.LOW_PADDING
import com.get_ready.core.ui.theme.Dimensions.SPACER_HEIGHT
import com.get_ready.core.ui.theme.blue
import com.get_ready.core.ui.theme.light_gray
import com.get_ready.core.ui.theme.white


@Composable
fun WeatherFactorsRow(weather: WeatherUIModel) {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = LOW_PADDING)
    ) {
        WeatherFactorCard(
            iconResId = R.drawable.ic_humidity,
            contentDescription = stringResource(R.string.humidity_icon),
            value = weather.humidity,
            label = stringResource(R.string.humidity),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = FACTOR_CARD_PADDING_HORIZONTAL)
        )
        WeatherFactorCard(
            iconResId = R.drawable.ic_wind_speed,
            contentDescription = stringResource(R.string.wind_speed_icon),
            value = weather.windSpeed,
            label = stringResource(R.string.wind_speed),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = FACTOR_CARD_PADDING_HORIZONTAL)
        )
        WeatherFactorCard(
            iconResId = R.drawable.ic_pressure,
            contentDescription = stringResource(R.string.pressure_icon),
            value = weather.pressure,
            label = stringResource(R.string.pressure),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = FACTOR_CARD_PADDING_HORIZONTAL)
        )
    }
}

@Composable
fun WeatherFactorCard(
    iconResId: Int,
    contentDescription: String,
    value: String,
    label: String,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = colorScheme.blue),
        shape = RoundedCornerShape(CARD_CORNER_RADIUS),
        border = BorderStroke(width = BORDER_WIDTH, color = colorScheme.light_gray),
        elevation = CardDefaults.cardElevation(defaultElevation = INNER_CARD_ELEVATION),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(vertical = HIGH_PADDING)
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(iconResId),
                contentDescription = contentDescription,
                tint = colorScheme.white,
                modifier = Modifier.size(FACTOR_ICON_SIZE)
            )
            Spacer(modifier = Modifier.height(SPACER_HEIGHT))
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = colorScheme.white,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(SPACER_HEIGHT))
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge.copy(color = colorScheme.white)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun WeatherFactorCardPreview() {
    WeatherFactorCard(
        iconResId = R.drawable.ic_humidity,
        contentDescription = stringResource(id = R.string.humidity_icon),
        value = "90%",
        label = "Humidity",
        Modifier.padding(8.dp)
    )
}

@Composable
@Preview(showBackground = true)
fun WeatherFactorRowPreview() {
    WeatherFactorsRow(
        WeatherUIModel(
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
