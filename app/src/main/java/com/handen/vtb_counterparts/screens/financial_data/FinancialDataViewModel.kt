package com.handen.vtb_counterparts.screens.financial_data

import androidx.lifecycle.viewModelScope
import com.betterlifeapps.std.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class FinancialDataViewModel @Inject constructor(private val financialDataService: FinancialDataService) :
    BaseViewModel() {
    val state = MutableStateFlow<UiState>(UiState.Loading)

    init {
        viewModelScope.launch {
            state.value = UiState.Loading
            kotlin.runCatching {
                financialDataService.getFinancialData()
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
        data class Loaded(val financialData: FinancialData) : UiState()
        object Error : UiState()
    }
}