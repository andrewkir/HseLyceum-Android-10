package ru.andrewkir.saturday10.presentation.showcase

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import ru.andrewkir.saturday10.data.models.GoodsItemModel
import ru.andrewkir.saturday10.presentation.goods.components.GoodsCard

@Composable
fun ShowcaseInput() {
  var text by rememberSaveable { mutableStateOf("") }
  val models = remember { mutableStateListOf<GoodsItemModel>() }

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
      models.add(
        GoodsItemModel(
          name = text,
          stars = 2,
          price = 3000,
          imageId = 0,
          imageURL = "https://i.pinimg.com/originals/0b/6a/b8/0b6ab8df78e80741a6539883b359faad.jpg"
        )
      )
      text = ""
    }) {
      Text("Add")
    }

    LazyColumn {
      models.forEach { goodModel ->
        item {
          GoodsCard(Modifier, goodModel)
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