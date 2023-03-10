package com.globalhiddenodds.tribe10androidtest.ui.extension

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun Modifier.basicButton(): Modifier {
    return this.fillMaxWidth().padding(16.dp, 8.dp).height(50.dp)
}

fun Modifier.fieldTextAreaModifier(): Modifier {
    return this.fillMaxWidth().padding(12.dp).height(100.dp)
}

fun Modifier.card(): Modifier {
    return this.padding(16.dp, 0.dp, 16.dp, 8.dp)
}

fun Modifier.spacer(): Modifier {
    return this.fillMaxWidth().padding(12.dp)
}

fun Modifier.smallSpacer(): Modifier {
    return this.fillMaxWidth().padding(8.dp)
}

fun Modifier.largeSpacer(): Modifier {
    return this.fillMaxWidth().padding(24.dp)
}