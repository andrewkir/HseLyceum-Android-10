package ru.andrewkir.saturday10

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andrewkir.saturday10.components.GoodsCard
import ru.andrewkir.saturday10.models.GoodsItemModel
import ru.andrewkir.saturday10.ui.theme.Saturday10Theme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    Log.d("MYTAG", "onCreate")

    enableEdgeToEdge()
    setContent {
      Saturday10Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          Column(modifier = Modifier.padding(innerPadding)) {
            MainContent()
          }
        }
      }
    }
  }
}

@Composable
fun MainContent() {
  val cards = listOf(
    GoodsItemModel(
      name = "Ershik",
      stars = 3,
      price = 12345,
      imageId = R.drawable.ershik
    ),

    GoodsItemModel(
      name = "Unitaz",
      stars = 5,
      price = 100000,
      imageId = R.drawable.toilet
    ),
  )

  LazyColumn {
    cards.forEach { item ->
      item {
        GoodsCard(
          Modifier.padding(12.dp),
          item
        )
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  Saturday10Theme {
    MainContent()
  }
}