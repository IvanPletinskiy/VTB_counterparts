package com.handen.vtb_counterparts.screens.counterparts

import androidx.lifecycle.viewModelScope
import com.betterlifeapps.std.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CounterpartsViewModel @Inject constructor(
    private val counterpartsService: CounterpartsService
) : BaseViewModel() {
    val state = MutableStateFlow<UiState>(UiState.Loading)

    init {
        viewModelScope.launch {
            state.value = UiState.Loading
            kotlin.runCatching {
                counterpartsService.getCounterparts()
            }.onSuccess {
                state.value = UiState.Loaded(it)
            }.onFailure {
                it.printStackTrace()
                state.value = UiState.Error
            }
        }
    }

    sealed class UiState {
        object Loading : UiState()
        data class Loaded(val counterparts: List<ItemCounterpart>) : UiState()
        object Error : UiState()
    }
}