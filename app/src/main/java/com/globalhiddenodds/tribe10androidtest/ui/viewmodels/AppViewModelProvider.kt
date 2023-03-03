package com.globalhiddenodds.tribe10androidtest.ui.viewmodels

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.globalhiddenodds.tribe10androidtest.HistoryApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            EntryViewModel(historyApplication().container.historiesRepository)
        }

        initializer {
            SplashViewModel(historyApplication().container.personRepository)
        }

        initializer {
            ReportViewModel(historyApplication().container.historiesRepository)
        }
    }
}

/**
 * Extension function to queries for Application object and returns an instance of
 * [HistoryApplication].
 */
fun CreationExtras.historyApplication() =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as HistoryApplication)