package com.get_ready.cityinput.viewModel

import com.get_ready.core.ui.BaseViewModel
import com.get_ready.domain.usecases.SaveLastSearchedCityUseCase
import com.get_ready.domain.usecases.SearchCityUseCase
import com.get_ready.cityinput.event.SaveLastSearchedCityEvent
import com.get_ready.cityinput.event.SaveLastSearchedCityEvent.Failure
import com.get_ready.cityinput.event.SaveLastSearchedCityEvent.Success
import com.get_ready.cityinput.intent.InputCityIntent
import com.get_ready.cityinput.mapper.toUICities
import com.get_ready.cityinput.viewState.InputCityViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class InputCityViewModel @Inject constructor(
    private val searchCityUseCase: SearchCityUseCase,
    private val saveLastSearchedCityUseCase: SaveLastSearchedCityUseCase,
) : BaseViewModel() {
    private val _viewState = MutableStateFlow(InputCityViewState())
    val viewState: StateFlow<InputCityViewState> = _viewState

    private val _citySaveEvent = MutableSharedFlow<SaveLastSearchedCityEvent>()
    val citySaveEvent: SharedFlow<SaveLastSearchedCityEvent> = _citySaveEvent

    private val _intent = MutableSharedFlow<InputCityIntent>()

    init {
        processIntents()
    }

    fun setIntent(intent: InputCityIntent) {
        launchOnIODispatcher {
            _intent.emit(intent)
        }
    }

    private fun processIntents() {
        launchOnIODispatcher {
            _intent.collectLatest { intent ->
                when (intent) {
                    is InputCityIntent.CitySelected -> {
                        onCitySelected(intent.cityName)
                    }

                    is InputCityIntent.SearchQueryChanged -> {
                        val searchQuery = intent.searchQuery
                        updateSearchQuery(searchQuery)
                        searchCities(searchQuery)
                    }
                }
            }
        }
    }

    private suspend fun onCitySelected(cityName: String) {
        try {
            saveLastSearchedCityUseCase(cityName)
            _citySaveEvent.emit(Success(cityName))
        } catch (exception: Exception) {
            _citySaveEvent.emit(
                Failure(exception.message ?: "Unknown error")
            )
        }
    }

    override fun handleException(exception: Throwable) {
        _viewState.value = _viewState.value.copy(
            errorMsg = exception.message ?: "Unexpected error occurred"
        )
    }

    private fun updateSearchQuery(query: String) {
        _viewState.value = _viewState.value.copy(
            searchQuery = query,
            noSuggestionsErrorMsg = null,
            errorMsg = null
        )
    }

    private suspend fun searchCities(query: String) {
        if (query.isEmpty()) {
            _viewState.value = _viewState.value.copy(suggestedCities = emptyList())
            return
        }
        _viewState.value = _viewState.value.copy(isLoading = true)
        try {
            val suggestedCities = searchCityUseCase(query)
            if (suggestedCities.isEmpty()) {
                _viewState.value = _viewState.value.copy(noSuggestionsErrorMsg = query)
            } else {
                _viewState.value =
                    _viewState.value.copy(suggestedCities = suggestedCities.toUICities())
            }
        } catch (e: Exception) {
            _viewState.value =
                _viewState.value.copy(errorMsg = e.message ?: "Failed to search for $query")
        } finally {
            _viewState.value = _viewState.value.copy(isLoading = false)
        }
    }
}