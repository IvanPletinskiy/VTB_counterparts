package com.handen.vtb_counterparts.screens.counterparts

import com.betterlifeapps.std.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf


class CounterpartViewModel : BaseViewModel() {
    val state = MutableStateFlow<UiState>(
        UiState.Loaded(
            listOf(
                Counterpart(11111116, "ОАО Рога и Копыта"),
                Counterpart(12312323, "АндерсенБЕЛ")
            )
        )
    )


    sealed class UiState() {
        object Loading : UiState()
        data class Loaded(val counterparts: List<Counterpart>) : UiState()
        object Error : UiState()
    }
}