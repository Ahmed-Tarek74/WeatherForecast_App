package com.get_ready.currentweather.composableScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.get_ready.core.ui.composables.AppBar
import com.get_ready.core.ui.composables.LoadingDialog
import com.get_ready.features.R
import com.get_ready.core.ui.composables.ErrorMsgCard
import com.get_ready.currentweather.composables.WeatherDetailsCard
import com.get_ready.core.ui.theme.Dimensions.MEDIUM_PADDING
import com.get_ready.currentweather.composables.CurrentCityDateCard
import com.get_ready.currentweather.viewStates.CurrentWeatherViewState

@Composable
fun CurrentWeatherScreen(
    viewState: State<CurrentWeatherViewState>,
    onEditCurrentCity: () -> Unit,
    toDailyForecasts: (String) -> Unit,
) {
    Scaffold(
        topBar = { AppBar(stringResource(id = R.string.app_title)) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when (val state = viewState.value) {
                is CurrentWeatherViewState.Error -> ErrorMsgCard(
                    errorMsg = state.errorMsg,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(MEDIUM_PADDING)
                )

                is CurrentWeatherViewState.Loading -> {
                    LoadingDialog(loadingMsg = stringResource(R.string.loading))
                }

                is CurrentWeatherViewState.Success -> {
                    val currentWeather = state.weather
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    )
                    {
                        CurrentCityDateCard(
                            currentWeather.cityName,
                            currentWeather.date,
                            onEditCurrentCity
                        )
                        WeatherDetailsCard(currentWeather)
                        ExtendedFloatingActionButton(
                            onClick = { toDailyForecasts(state.weather.cityName) },
                            content = {
                                Text(
                                    text = stringResource(R.string.daily_forecast_btn),
                                    style = MaterialTheme.typography.headlineMedium
                                )
                                Icon(
                                    Icons.AutoMirrored.Filled.ArrowForward,
                                    stringResource(R.string.daily_forecast_btn)
                                )
                            },
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                }
            }
        }
    }
}
