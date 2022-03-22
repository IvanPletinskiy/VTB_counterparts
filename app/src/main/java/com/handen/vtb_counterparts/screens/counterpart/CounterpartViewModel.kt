package com.handen.vtb_counterparts.screens.counterpart

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.handen.vtb_counterparts.screens.counterparts.CounterpartsService
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CounterpartViewModel @AssistedInject constructor(
    @Assisted counterpartId: Int,
    @ApplicationContext context: Context,
    private val counterpartsService: CounterpartsService
) : ViewModel() {

    val state = MutableStateFlow<UiState>(UiState.Loading)

    init {
        viewModelScope.launch {
            state.value = UiState.Loading
            kotlin.runCatching {
                counterpartsService.getCounterpart(counterpartId.toString())
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
        data class Loaded(val counterpart: Counterpart) : UiState()
        object Error : UiState()
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(id: Int): CounterpartViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            id: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(id) as T
            }
        }
    }

}