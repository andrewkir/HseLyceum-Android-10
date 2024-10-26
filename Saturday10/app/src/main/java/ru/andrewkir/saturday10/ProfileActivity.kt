package ru.andrewkir.saturday10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import ru.andrewkir.saturday10.ui.theme.Saturday10Theme

class ProfileActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      Saturday10Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          val name = intent.extras?.getString("name")
          Text(modifier = Modifier.padding(innerPadding), text = "Hello $name!", fontSize = 24.sp)
        }
      }
    }
  }
}