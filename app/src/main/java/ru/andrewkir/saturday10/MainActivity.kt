package ru.andrewkir.saturday10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import ru.andrewkir.saturday10.features.NavGraphs
import ru.andrewkir.saturday10.features.destinations.AstroScreenDestination
import ru.andrewkir.saturday10.navigation.BottomBar
import ru.andrewkir.saturday10.theme.Saturday10Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            Saturday10Theme {
//                DestinationsNavHost(navGraph = NavGraphs.root)

                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomBar(navController)
                    }
                ) {
                    DestinationsNavHost(
                        navController = navController,
                        navGraph = NavGraphs.root,
                        modifier = Modifier
                            .padding(it)
                            .fillMaxSize(),
                        startRoute = AstroScreenDestination
                    )
                }
            }
        }
    }
}