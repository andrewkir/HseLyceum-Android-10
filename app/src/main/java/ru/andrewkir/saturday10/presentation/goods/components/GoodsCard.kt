package ru.andrewkir.saturday10.presentation.goods.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ContentScale.Companion
import androidx.compose.ui.res.painterResource
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
  ElevatedCard {
    Image(
      modifier = Modifier.height(170.dp),
      painter = painterResource(R.drawable.ershik),
      contentScale = ContentScale.Crop,
      contentDescription = null
    )
    Row(
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        modifier = modifier
          .padding(16.dp),
        text = item.name
      )

      Row {
        for (i in 1..item.stars) {
          Icon(imageVector = Icons.Filled.Star, contentDescription = null)
        }
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
      stars = 5,
      imageId = R.drawable.ershik,
      price = 10000,
      imageURL = "https://i.ytimg.com/vi/fEutmfmgN6M/maxresdefault.jpg",
    )
  )
}