package ru.andrewkir.saturday10.features.github.presentation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalUriHandler
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest
import ru.andrewkir.saturday10.features.destinations.DetailsScreenDestination
import ru.andrewkir.saturday10.features.github.presentation.contract.GithubUsersUIEffect.OpenUrl
import ru.andrewkir.saturday10.features.github.presentation.contract.GithubUsersUIEffect.ShowNotification

@Composable
@Destination(start = true)
fun GithubScreen(
  navigator: DestinationsNavigator
) {
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
          navigator.navigate(DetailsScreenDestination(effect.url))
        }
      }
    }
  }

  GithubScreenContent(
    uiState = state,
    snackbarHostState = snackbarHostState,
    onEvent = viewModel::setEvent,
  )
}