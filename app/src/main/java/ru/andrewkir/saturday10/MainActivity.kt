package ru.andrewkir.saturday10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ru.andrewkir.saturday10.animals.presentation.AnimalsScreen
import ru.andrewkir.saturday10.legacy.presentation.goods.theme.Saturday10Theme

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    enableEdgeToEdge()
    setContent {
      Saturday10Theme {
        AnimalsScreen()
      }
    }
  }
}