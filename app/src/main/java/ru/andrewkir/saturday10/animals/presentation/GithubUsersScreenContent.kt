package ru.andrewkir.saturday10.animals.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import ru.andrewkir.saturday10.animals.presentation.contract.AnimalsUIState
import ru.andrewkir.saturday10.animals.presentation.contract.GithubUsersUIEvent

@Composable
fun AnimalScreenContent(
  uiState: AnimalsUIState,
  snackbarHostState: SnackbarHostState,
  onEvent: (GithubUsersUIEvent) -> Unit,
) {
  Box {
    if (uiState.isLoading) {
      CircularProgressIndicator(
        modifier = Modifier
          .width(64.dp)
          .align(Alignment.Center)
          .zIndex(1f)
      )
    }

    Scaffold(
      snackbarHost = {
        SnackbarHost(
          hostState = snackbarHostState,
          modifier = Modifier.imePadding()
        )
      },
      floatingActionButton = {
        if (!uiState.isLoading) {
          FloatingActionButton(
            onClick = { onEvent(GithubUsersUIEvent.OnRefreshClick) },
            content = {
              Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = null
              )
            }
          )
        }
      }
    ) { paddingValues ->
      Column(
        modifier = Modifier
          .padding(paddingValues)
      ) {
        LazyColumn {
          items(uiState.users) { user ->
            Text(
              modifier = Modifier
                .padding(8.dp)
                .fillParentMaxWidth(),
              text = user.login,
              fontSize = 24.sp
            )
          }
        }
      }
    }
  }
}

@Composable
@Preview
private fun AnimalScreenContentPreview() {
  AnimalScreenContent(
    uiState = AnimalsUIState(),
    snackbarHostState = SnackbarHostState(),
    onEvent = {},
  )
}