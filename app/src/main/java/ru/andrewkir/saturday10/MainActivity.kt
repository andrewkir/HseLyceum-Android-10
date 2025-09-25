package ru.andrewkir.saturday10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ramcosta.composedestinations.DestinationsNavHost
import ru.andrewkir.saturday10.features.NavGraphs
import ru.andrewkir.saturday10.features.astro.presentation.AstroScreenContent
import ru.andrewkir.saturday10.features.astro.presentation.model.AstronautUI
import ru.andrewkir.saturday10.theme.Saturday10Theme

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    enableEdgeToEdge()
    setContent {
      Saturday10Theme {
        AstroScreenContent(
          astronauts = listOf(
            AstronautUI(
              craft = "ISS",
              name = "Oleg Kononenko"
            ),
            AstronautUI(
              craft = "ISS",
              name = "Nikolai Chub"
            ),
          )
        )
      }
    }
  }
}