package com.get_ready.cityinput.composableScreens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.get_ready.cityinput.R
import com.get_ready.cityinput.composables.SuggestedCitiesList
import com.get_ready.cityinput.event.SaveLastSearchedCityEvent
import com.get_ready.cityinput.event.SaveLastSearchedCityEvent.Failure
import com.get_ready.cityinput.event.SaveLastSearchedCityEvent.Success
import com.get_ready.cityinput.intent.InputCityIntent
import com.get_ready.cityinput.intent.InputCityIntent.CitySelected
import com.get_ready.cityinput.intent.InputCityIntent.SearchQueryChanged
import com.get_ready.cityinput.viewState.InputCityViewState
import com.get_ready.core.ui.composables.ErrorMsgCard
import com.get_ready.core.ui.composables.SearchInputBar
import com.get_ready.core.ui.theme.Dimensions.SPACER_HEIGHT
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SearchCityInputScreen(
    viewState: State<InputCityViewState>,
    citySaveEvent: Flow<SaveLastSearchedCityEvent>,
    setIntent: (InputCityIntent) -> Unit,
    toCurrentWeather: (String) -> Unit,
) {
    val context = LocalContext.current
    LaunchedEffect(citySaveEvent) {
        citySaveEvent.collectLatest { event ->
            when (event) {
                is Success -> {
                    showToast(
                        context,
                        context.getString(R.string.city_saved_successfully, event.savedCity)
                    )
                    toCurrentWeather(event.savedCity)
                }

                is Failure -> {
                    context.getString(R.string.failed_to_save_city, event.errorMsg)
                }
            }
        }
    }
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .background(color = colorScheme.background)
            .fillMaxSize()
    ) {
        SearchInputBar(
            searchHint = stringResource(R.string.search_hint),
            searchQuery = viewState.value.searchQuery,
            modifier = Modifier.fillMaxWidth()
        ) { newQuery ->
            setIntent(SearchQueryChanged(newQuery))
        }
        Spacer(modifier = Modifier.height(SPACER_HEIGHT))
        when {
            viewState.value.isLoading -> {
                CircularProgressIndicator(
                    color = colorScheme.onPrimaryContainer,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            viewState.value.noSuggestionsErrorMsg != null -> {
                Text(
                    text = stringResource(
                        R.string.no_result_found_for,
                        viewState.value.searchQuery
                    ),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            viewState.value.errorMsg != null -> {
                ErrorMsgCard(errorMsg = viewState.value.errorMsg!!)
            }

            else -> {
                SuggestedCitiesList(
                    suggestedCities = viewState.value.suggestedCities,
                    onCitySelected = {setIntent(CitySelected(it)) },
                )
            }
        }
    }
}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}