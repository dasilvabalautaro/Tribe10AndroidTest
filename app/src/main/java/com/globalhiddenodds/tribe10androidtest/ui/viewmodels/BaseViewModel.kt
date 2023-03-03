package com.globalhiddenodds.tribe10androidtest.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.globalhiddenodds.tribe10androidtest.ui.tools.SnackbarManager
import com.globalhiddenodds.tribe10androidtest.ui.tools.SnackbarMessage.Companion.toSnackbarMessage
import kotlinx.coroutines.CoroutineExceptionHandler

open class BaseViewModel: ViewModel() {
    open val showErrorExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError(throwable)
    }

    open fun onError(error: Throwable) {
        SnackbarManager.showMessage(error.toSnackbarMessage())
    }
}