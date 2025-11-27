package ru.andrewkir.saturday10

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import ru.andrewkir.saturday10.features.NavGraphs
import ru.andrewkir.saturday10.theme.Saturday10Theme
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    //внутреннее использование камеры
    private lateinit var cameraExecutor: ExecutorService
    var shouldShowCamera: MutableState<Boolean> = mutableStateOf(false)

    val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Log.i("CameraPermission", "Camera Permission granted")
            shouldShowCamera.value = true
        } else {
            Log.i("CameraPermission", "Camera Permission denied")
            shouldShowCamera.value = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cameraExecutor = Executors.newSingleThreadExecutor()

        requestCameraPermission()

        enableEdgeToEdge()
        setContent {
            Saturday10Theme {
                DestinationsNavHost(navGraph = NavGraphs.root)
//
//                val navController = rememberNavController()
//
//                Column {
//                    ImagePicker()
//                }
//
//                Scaffold(
//                    modifier = Modifier.fillMaxSize(),
//                    bottomBar = {
//                        BottomBar(navController)
//                    }
//                ) {
//                    DestinationsNavHost(
//                        navController = navController,
//                        navGraph = NavGraphs.root,
//                        modifier = Modifier
//                            .padding(it)
//                            .fillMaxSize(),
//                        startRoute = AstroScreenDestination
//                    )
//                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        requestCameraPermission()
    }
}

fun MainActivity.requestCameraPermission() {
    when {
        ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED -> {
            Log.i("CameraPermission", "Permission previously granted")
            this.shouldShowCamera.value = true
        }

        ActivityCompat.shouldShowRequestPermissionRationale(
            this,
            Manifest.permission.CAMERA
        ) -> Log.i("CameraPermission", "Show camera permissions dialog")

        else -> requestPermissionLauncher.launch(Manifest.permission.CAMERA)
    }
}