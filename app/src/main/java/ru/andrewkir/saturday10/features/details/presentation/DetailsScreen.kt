package ru.andrewkir.saturday10.features.details.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun DetailsScreen(
  url: String
) {
  Scaffold {
    Column(modifier = Modifier.padding(it)) {
      Text(url)
    }
  }
}