package ru.andrewkir.saturday10.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EnterUsername(
  onButtonClick: (String) -> Unit,
) {
  var name by rememberSaveable { mutableStateOf("") }

  Row(
    modifier = Modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically
  ) {
    TextField(value = name,
      onValueChange = { changedValue -> name = changedValue },
      label = { Text("Enter name") })

    Button(onClick = {
      onButtonClick(name)
    }) {
      Text(text = "Добавить")
    }
  }
}

@Composable
@Preview
private fun EnterUsernamePreview() {
  EnterUsername() {}
}