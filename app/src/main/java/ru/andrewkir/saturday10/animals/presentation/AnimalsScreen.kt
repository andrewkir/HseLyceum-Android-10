package ru.andrewkir.saturday10.animals.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AnimalsScreen() {
  val viewModel = viewModel<AnimalsViewModel>()
  val state by viewModel.state.collectAsState()

  AnimalScreenContent(
    uiState = state,
    onEvent = viewModel::onEvent,
  )
}