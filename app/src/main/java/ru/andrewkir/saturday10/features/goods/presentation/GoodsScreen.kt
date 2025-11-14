package ru.andrewkir.saturday10.features.goods.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination(start = true)
fun GoodsScreen() {
    val viewModel = viewModel<GoodsViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    GoodsScreenContent(
        uiState = uiState,
        onEvent = viewModel::setEvent
    )
}