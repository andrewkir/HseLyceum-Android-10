package ru.andrewkir.saturday10.presentation.goods.components

import android.util.Log
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ru.andrewkir.saturday10.R
import ru.andrewkir.saturday10.data.models.GoodsItemModel

@OptIn(ExperimentalLayoutApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun GoodsCard(
  modifier: Modifier,
  item: GoodsItemModel,
) {
  ElevatedCard(
    modifier = modifier.fillMaxWidth()
  ) {

    GlideImage(
      model = item.imageURL,
      contentDescription = null,
    )

    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Text(
        modifier = Modifier
          .padding(start = 12.dp, top = 16.dp, bottom = 16.dp),
        text = item.name,
        fontSize = 24.sp
      )
      Spacer(modifier = Modifier.weight(1f))
      FlowRow(modifier = Modifier.width(120.dp)) {
        for (i in 1..10) {
          Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = null,
            tint = if (i <= item.stars) {
              Color.Yellow
            } else {
              Color.Black
            }
          )
        }
      }
      Spacer(modifier = Modifier.padding(6.dp))
    }

    Row {
      Text(
        modifier = Modifier.padding(14.dp),
        text = "Всего за ${item.price} $",
        fontSize = 20.sp
      )

      Spacer(modifier = Modifier.weight(1f))

      Button(
        modifier = Modifier.padding(end = 14.dp),
        onClick = {
          Log.d("MYTAG", "On button clicked")
        }
      ) {
        Text(text = "Купить")
      }
    }
  }
}

@Composable
@Preview
private fun GoodsCardPreview() {
  GoodsCard(
    Modifier,
    GoodsItemModel(
      name = "Ершик",
      stars = 3,
      imageId = R.drawable.ershik,
      price = 10000,
      imageURL = "https://i.ytimg.com/vi/fEutmfmgN6M/maxresdefault.jpg",
    )
  )
}