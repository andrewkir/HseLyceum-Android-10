package ru.andrewkir.saturday10.presentation.goods.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ru.andrewkir.saturday10.data.models.UserModel
import ru.andrewkir.saturday10.presentation.goods.contract.GoodsEvent

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun UserCard(
  userModel: UserModel,
  onEvent: (GoodsEvent) -> Unit,
) {
  ElevatedCard(
    onClick = {
      onEvent(GoodsEvent.OnUserItemClick(userModel))
    }
  ) {

    GlideImage(
      model = userModel.imageUrl,
      contentDescription = null,
    )

    Text(
      modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth(),
      text = userModel.login,
      fontSize = 24.sp
    )

    Text(
      modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth(),
      text = userModel.id,
      fontSize = 24.sp
    )
  }
}

@Composable
@Preview
private fun UserCardPreview() {
  UserCard(
    UserModel("test", "1", ""), {}
  )
}