package com.globalhiddenodds.tribe10androidtest.ui.screens.report

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.globalhiddenodds.tribe10androidtest.data.History
import com.globalhiddenodds.tribe10androidtest.ui.components.ActionToolbar
import com.globalhiddenodds.tribe10androidtest.ui.components.TextDescription
import com.globalhiddenodds.tribe10androidtest.ui.components.ZoomImage
import com.globalhiddenodds.tribe10androidtest.ui.viewmodels.AppViewModelProvider
import com.globalhiddenodds.tribe10androidtest.ui.viewmodels.ReportViewModel
import com.globalhiddenodds.tribe10androidtest.R.drawable as AppIco
import com.globalhiddenodds.tribe10androidtest.R.string as AppText

@Composable
fun ReportScreen(
    popUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ReportViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val reportUiSate by viewModel.reportUiState.collectAsState()

    Column(modifier = modifier) {
        ActionToolbar(
            AppText.lbl_list_history,
            AppIco.camera,
            modifier = modifier, popUp)

        ReportBody(
            historyList = reportUiSate.historyList,
            onSendZoomImage = {}
        )
    }
}

@Composable
private fun ReportBody(
    historyList: List<History>,
    onSendZoomImage: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (historyList.isEmpty()){
            Text(
                text = stringResource(AppText.lbl_list_empty),
                style = MaterialTheme.typography.subtitle2
            )
        } else {
            LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(items = historyList, key = { it.id }) { history ->
                    HistoryItem(history = history, onSendZoomImage)
                }
            }
        }
    }
}

@Composable
private fun HistoryItem(
    history: History,
    onSendZoomImage: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(4.dp),
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            history.image?.let { ZoomImage(byteImage = it, onSendZoomImage) }
            TextDescription(label = history.description)
        }
    }
}