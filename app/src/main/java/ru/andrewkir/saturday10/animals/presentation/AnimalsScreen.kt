package ru.andrewkir.saturday10.animals.presentation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collectLatest
import ru.andrewkir.saturday10.animals.presentation.contract.AnimalsUIEffect.ShowNotification

@Composable
fun AnimalsScreen() {
  val viewModel = viewModel<AnimalsViewModel>()
  val state by viewModel.state.collectAsState()

  val snackbarHostState = remember { SnackbarHostState() }

  LaunchedEffect(viewModel.effect) {
    viewModel.effect.collectLatest { effect ->
      when(effect){
        is ShowNotification -> {
          snackbarHostState.showSnackbar(effect.message)
        }
      }
    }
  }

  AnimalScreenContent(
    uiState = state,
    snackbarHostState = snackbarHostState,
    onEvent = viewModel::onEvent,
  )
}