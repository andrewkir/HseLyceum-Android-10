package ru.andrewkir.saturday10.presentation.details.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun DetailsScreenContent(
  name: String,
) {
  Column {
    Text("Hello $name!", fontSize = 24.sp)
  }
}