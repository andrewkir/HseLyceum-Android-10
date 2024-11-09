package ru.andrewkir.saturday10.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.andrewkir.saturday10.components.GoodsScreenContent
import ru.andrewkir.saturday10.viewmodels.GoodsViewModel

@Composable
fun GoodsScreen() {

  val viewModel = viewModel<GoodsViewModel>()

  val state by viewModel.state.collectAsState()

  GoodsScreenContent(
    state
  )
}