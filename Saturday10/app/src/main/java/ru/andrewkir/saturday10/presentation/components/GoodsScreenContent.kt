package ru.andrewkir.saturday10.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andrewkir.saturday10.R
import ru.andrewkir.saturday10.data.models.GoodsItemModel
import ru.andrewkir.saturday10.presentation.contract.GoodsEvent
import ru.andrewkir.saturday10.presentation.contract.GoodsState

@Composable
fun GoodsScreenContent(
  uiState: GoodsState,
  onEvent: (GoodsEvent) -> Unit,
) {
  Column {
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      TextField(value = uiState.goodsName,
        onValueChange = { changedValue -> onEvent(GoodsEvent.UpdateGoodsTextField(changedValue)) },
        label = { Text("Enter") })

      Button(
        modifier = Modifier.padding(start = 14.dp),
        onClick = {
          onEvent(GoodsEvent.AddButtonClicked)
        }) {
        Text(text = "Add")
      }
    }

    LazyColumn {
      uiState.goods.forEach { item ->
        item {
          GoodsCard(
            Modifier.padding(12.dp),
            item
          )
        }
      }
    }
  }
}

@Preview
@Composable
private fun GoodsScreenContentPreview() {
  GoodsScreenContent(
    uiState = GoodsState(
      goodsName = "test",
      goods = listOf(
        GoodsItemModel(
          name = "Name",
          stars = 3,
          price = 123123,
          imageId = R.drawable.ershik
        )
      )
    )
  ) { _ -> }
}