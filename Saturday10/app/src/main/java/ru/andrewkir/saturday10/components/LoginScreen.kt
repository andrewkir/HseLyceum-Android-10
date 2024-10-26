package ru.andrewkir.saturday10.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
  modifier: Modifier,
  onLoginClick: (String, String) -> Unit,
) {
  var name by rememberSaveable { mutableStateOf("") }
  var password by rememberSaveable { mutableStateOf("") }

  Column(
    modifier = modifier
      .fillMaxWidth()
      .fillMaxHeight(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    OutlinedTextField(
      value = name,
      onValueChange = { changedValue -> name = changedValue },
      label = { Text("Username") }
    )

    OutlinedTextField(
      value = password,
      visualTransformation = PasswordVisualTransformation(),
      onValueChange = { changedValue -> password = changedValue },
      label = { Text("Password") }
    )

    Button(
      modifier = Modifier
        .padding(16.dp)
        .width(180.dp),
      onClick = {
        onLoginClick(name, password)
      }) {
      Text(text = "Login")
    }
  }
}

@Composable
@Preview
private fun LoginScreenPreview() {
  LoginScreen(Modifier) { _, _ -> }
}