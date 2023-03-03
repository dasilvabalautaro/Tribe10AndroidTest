package com.globalhiddenodds.tribe10androidtest.ui.viewmodels

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.globalhiddenodds.tribe10androidtest.REPORT_SCREEN
import com.globalhiddenodds.tribe10androidtest.data.History
import com.globalhiddenodds.tribe10androidtest.data.HistoryRepository
import com.globalhiddenodds.tribe10androidtest.ui.screens.entry.EntryUiState
import com.globalhiddenodds.tribe10androidtest.ui.tools.SnackbarManager
import com.globalhiddenodds.tribe10androidtest.ui.tools.Transform
import kotlinx.coroutines.launch
import com.globalhiddenodds.tribe10androidtest.R.drawable as AppIco
import com.globalhiddenodds.tribe10androidtest.R.string as AppText

class EntryViewModel(
    private val historyRepository: HistoryRepository
) : BaseViewModel() {
    val uiState = mutableStateOf(EntryUiState())

    fun initialize(context: Context) {
        uiState.value = uiState.value.copy(image = Transform.getBitmapFromDrawable(context, AppIco.camera_google))
        uiState.value = uiState.value.copy(description = "")
    }

    fun onDescriptionChange(newValue: String) {
        uiState.value = uiState.value.copy(description = newValue)
    }

    fun onImageChange(newValue: Bitmap) {
        viewModelScope.launch(showErrorExceptionHandler) {
            uiState.value = uiState.value.copy(image = Transform.resizeBitmap(newValue))
            uiState.value = uiState.value.copy(capture = true)
        }

    }

    fun onSaveHistory(navigate: (String) -> Unit) {
        viewModelScope.launch(showErrorExceptionHandler) {

            if (validateInput()) {
                val byteImage =
                    uiState.value.image?.let { Transform.bitmapToByteArray(it) }
                historyRepository.insertHistory(
                    item = History(
                        0,
                        uiState.value.description,
                        byteImage
                    )
                )
                navigate(REPORT_SCREEN)
            } else {
                SnackbarManager.showMessage(AppText.empty_fields)
            }

        }
    }

    private fun validateInput(): Boolean {
        return with(uiState.value) {
            description.isNotBlank() && capture
        }
    }

    fun clear(context: Context) {
        uiState.value = uiState.value.copy(
            description = "",
            image = Transform.getBitmapFromDrawable(context, AppIco.camera_google),
            capture = false)
    }
}