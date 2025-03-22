package ru.andrewkir.saturday10.animals.presentation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalUriHandler
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collectLatest
import ru.andrewkir.saturday10.animals.presentation.contract.GithubUsersUIEffect.OpenUrl
import ru.andrewkir.saturday10.animals.presentation.contract.GithubUsersUIEffect.ShowNotification

@Composable
fun AnimalsScreen() {
  val viewModel = viewModel<GithubUsersViewModel>()
  val state by viewModel.uiState.collectAsState()

  val snackbarHostState = remember { SnackbarHostState() }
  val uriHandler = LocalUriHandler.current

  LaunchedEffect(viewModel.effect) {
    viewModel.effect.collectLatest { effect ->
      when (effect) {
        is ShowNotification -> {
          snackbarHostState.showSnackbar(effect.message)
        }

        is OpenUrl -> {
          uriHandler.openUri(effect.url)
        }
      }
    }
  }

  AnimalScreenContent(
    uiState = state,
    snackbarHostState = snackbarHostState,
    onEvent = viewModel::setEvent,
  )
}