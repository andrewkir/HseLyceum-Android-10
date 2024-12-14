package ru.andrewkir.saturday10.presentation.details.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import ru.andrewkir.saturday10.data.models.UserModel

@Destination
@Composable
fun DetailsScreenContent(
  user: UserModel,
) {
  Column {
    Text("Hello ${user.login}!", fontSize = 24.sp)
  }
}