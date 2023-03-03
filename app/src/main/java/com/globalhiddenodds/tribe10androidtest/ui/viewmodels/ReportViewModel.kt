package com.globalhiddenodds.tribe10androidtest.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.globalhiddenodds.tribe10androidtest.data.HistoryRepository
import com.globalhiddenodds.tribe10androidtest.ui.screens.report.ReportUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ReportViewModel(
    historyRepository: HistoryRepository): BaseViewModel() {

    val reportUiState: StateFlow<ReportUiState> =
        historyRepository.getAllHistoriesStream().map { ReportUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ReportUiState()
            )


    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}