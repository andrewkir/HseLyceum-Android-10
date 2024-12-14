package ru.andrewkir.saturday10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.DestinationsNavHost
import ru.andrewkir.saturday10.data.SharedPrefsRepository
import ru.andrewkir.saturday10.presentation.screen.GoodsScreen
import ru.andrewkir.saturday10.presentation.screen.NavGraphs
import ru.andrewkir.saturday10.presentation.theme.Saturday10Theme

class MainActivity : ComponentActivity() {

  private lateinit var sharedPrefsRepository: SharedPrefsRepository

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    sharedPrefsRepository = SharedPrefsRepository(baseContext)

    enableEdgeToEdge()
    setContent {
      Saturday10Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          Column(modifier = Modifier.padding(innerPadding)) {
            DestinationsNavHost(navGraph = NavGraphs.root)
          }
        }
      }
    }
  }

  @Preview(showBackground = true)
  @Composable
  fun GreetingPreview() {
    Saturday10Theme {
      GoodsScreen()
    }
  }
}