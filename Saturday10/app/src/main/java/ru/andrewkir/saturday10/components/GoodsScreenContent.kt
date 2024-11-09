package ru.andrewkir.saturday10.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.andrewkir.saturday10.contract.GoodsState

@Composable
fun GoodsScreenContent(
  uiState: GoodsState
) {
  LazyColumn {
    uiState.goods?.forEach { item ->
      item {
        GoodsCard(
          Modifier.padding(12.dp),
          item
        )
      }
    }
  }
}