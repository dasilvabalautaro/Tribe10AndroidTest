package com.globalhiddenodds.tribe10androidtest.ui.screens.entry

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.RestoreFromTrash
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.globalhiddenodds.tribe10androidtest.ui.components.ActionToolbar
import com.globalhiddenodds.tribe10androidtest.ui.components.BasicButton
import com.globalhiddenodds.tribe10androidtest.ui.components.LoadImageFromBitmap
import com.globalhiddenodds.tribe10androidtest.ui.components.TextAreaField
import com.globalhiddenodds.tribe10androidtest.ui.extension.basicButton
import com.globalhiddenodds.tribe10androidtest.ui.extension.fieldTextAreaModifier
import com.globalhiddenodds.tribe10androidtest.ui.viewmodels.AppViewModelProvider
import com.globalhiddenodds.tribe10androidtest.ui.viewmodels.EntryViewModel
import kotlin.system.exitProcess
import com.globalhiddenodds.tribe10androidtest.R.drawable as AppIco
import com.globalhiddenodds.tribe10androidtest.R.string as AppText

@Composable
fun EntryScreen(
    navigate: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by viewModel.uiState
    val context = LocalContext.current
    var stateScreen by rememberSaveable {
        mutableStateOf(false)
    }

    val loadImage = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicturePreview()) {
        if (it != null) {
            viewModel.onImageChange(newValue = it)
        }
    }

    LaunchedEffect(Unit) {
        if (!stateScreen) {
            stateScreen = true
            viewModel.initialize(context)
        }

    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ActionToolbar(
            AppText.create_report,
            AppIco.close,
            modifier = modifier, endAction = {
                android.os.Process.SIGNAL_KILL
                exitProcess(1)
            })
        LoadImageFromBitmap(value = viewModel.uiState.value.image, 500.dp, context)
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                onClick = {
                    loadImage.launch()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Camera,
                    contentDescription = stringResource(id = AppText.lbl_click_photo),
                    tint = MaterialTheme.colors.primaryVariant
                )
            }

            IconButton(
                onClick = {
                    viewModel.clear(context)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.RestoreFromTrash,
                    contentDescription = stringResource(id = AppText.lbl_clear),
                    tint = MaterialTheme.colors.primaryVariant
                )
            }
        }

        TextAreaField(
            AppText.lbl_description,
            uiState.description,
            viewModel::onDescriptionChange, Modifier.fieldTextAreaModifier())

        BasicButton(AppText.lbl_save_history,
            Modifier.basicButton()
        ) {
            viewModel.onSaveHistory(navigate)
        }
    }
}