package ru.andrewkir.saturday10.presentation.showcase

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShowcaseInput() {
  var text by rememberSaveable { mutableStateOf("") }
  val todo = remember { mutableStateListOf<String>() }

  Column(Modifier.fillMaxWidth()) {
    OutlinedTextField(
      modifier = Modifier.fillMaxWidth(),
      value = text,
      label = {
        Text("Название")
      },
      onValueChange = {
        text = it
      }
    )

    Button(onClick = {
      todo.add(text)
      text = ""
    }) {
      Text("Add")
    }

    LazyColumn {
      todo.forEach {
        item {
          Text(
            modifier = Modifier.padding(8.dp),
            text = "- $it",
            fontSize = 24.sp
          )
        }
      }
    }
  }
}

@Composable
@Preview
private fun ShowcaseInputPreview() {
  ShowcaseInput()
}