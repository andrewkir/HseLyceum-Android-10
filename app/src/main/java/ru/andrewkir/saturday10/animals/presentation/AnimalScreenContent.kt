package ru.andrewkir.saturday10.animals.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
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
import ru.andrewkir.saturday10.animals.presentation.contract.AnimalsUIEvent
import ru.andrewkir.saturday10.animals.presentation.contract.AnimalsUIState

@Composable
fun AnimalScreenContent(
  uiState: AnimalsUIState,
  snackbarHostState: SnackbarHostState,
  onEvent: (AnimalsUIEvent) -> Unit,
) {
  Scaffold(
    snackbarHost = {
      SnackbarHost(
        hostState = snackbarHostState,
        modifier = Modifier.imePadding()
      )
    }
  ) { paddingValues ->
    Column(modifier = Modifier.padding(paddingValues)) {
      Row(
        verticalAlignment = Alignment.CenterVertically
      ) {
        OutlinedTextField(
          modifier = Modifier.padding(horizontal = 12.dp),
          value = uiState.nameInput,
          onValueChange = { changedValue ->
            onEvent(AnimalsUIEvent.OnNameInputChanged(changedValue))
          },
          placeholder = {
            Text("Введите имя")
          }
        )

        Button(
          onClick = {
            onEvent(AnimalsUIEvent.OnAddButtonClicked)
          }) {
          Text("Add")
        }
      }

      OutlinedTextField(
        modifier = Modifier.padding(horizontal = 12.dp),
        value = uiState.surnameInput,
        onValueChange = { changedValue ->
          onEvent(AnimalsUIEvent.OnSurnameInputChanged(changedValue))
        },
        placeholder = {
          Text("Введите имя")
        }
      )

      LazyColumn {
        uiState.users.forEach { user ->
          item {
            Text(
              modifier = Modifier.padding(8.dp),
              text = user.name,
              fontSize = 24.sp
            )
            Text(
              modifier = Modifier.padding(8.dp),
              text = user.surname,
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