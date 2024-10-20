package com.get_ready.dailyforecasts.composableScreens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.get_ready.core.ui.composables.AppBar
import com.get_ready.features.R
import com.get_ready.dailyforecasts.composables.DailyForecastList
import com.get_ready.core.ui.composables.ErrorMsgCard
import com.get_ready.core.ui.composables.LoadingDialog
import com.get_ready.core.ui.theme.Dimensions.MEDIUM_PADDING
import com.get_ready.dailyforecasts.viewStates.DailyForecastViewState

@Composable
fun DailyForecastScreen(viewState: State<DailyForecastViewState>, navigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            AppBar(title = stringResource(R.string.daily_forecast_title)) {
                IconButton(onClick = navigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Localized description"
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val state = viewState.value) {
                is DailyForecastViewState.Error -> {
                    ErrorMsgCard(
                        errorMsg = state.errorMsg,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(MEDIUM_PADDING)
                    )
                }
                is DailyForecastViewState.Loading -> {
                    LoadingDialog(loadingMsg = stringResource(R.string.loading))
                }
                is DailyForecastViewState.Success -> {
                    DailyForecastList(dailyForecasts = state.dailyForecasts)

                }
            }
        }
    }
}