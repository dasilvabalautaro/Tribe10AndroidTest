package com.globalhiddenodds.tribe10androidtest.ui.screens.entry

import android.graphics.Bitmap

data class EntryUiState(
    val description: String = "",
    val image: Bitmap? = null,
    val capture: Boolean = false
)
