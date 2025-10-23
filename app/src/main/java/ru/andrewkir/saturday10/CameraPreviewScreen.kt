package ru.andrewkir.saturday10

import android.content.ContentValues
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.FileProvider
import java.io.File
import java.util.concurrent.ExecutorService

@Composable
fun CameraPreviewScreen(cameraExecutor: ExecutorService) {
    val context = LocalContext.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    val previewView = remember { PreviewView(context) }

    // State to control the selected saving option
    var selectedOption by remember { mutableStateOf(0) }

    // Remember the imageCapture instance
    val imageCapture = remember { ImageCapture.Builder().build() }

    // Initialize camera preview
    LaunchedEffect(Unit) {
        val cameraProvider = cameraProviderFuture.get()
        val preview = Preview.Builder().build().also {
            it.surfaceProvider = previewView.surfaceProvider
        }
        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        try {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                context as ComponentActivity,
                cameraSelector,
                preview,
                imageCapture
            )
        } catch (exc: Exception) {
            Log.e("CameraXApp", "Use case binding failed", exc)
        }
    }

    fun capturePhoto() {
        // Create output options based on the selected option
        val outputOptions = when (selectedOption) {
            0 -> {
                // Saving to a File
                val file = File(context.filesDir, "captured_image.jpg")
                ImageCapture.OutputFileOptions.Builder(file).build()
            }

            1 -> {
                // Saving to MediaStore
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, "captured_image.jpg")
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }
                ImageCapture.OutputFileOptions.Builder(
                    context.contentResolver,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    contentValues
                ).build()
            }

            2 -> {
                // Saving to a File exposed via FileProvider URI
                val file = File(context.filesDir, "captured_image.jpg")
                val uri = FileProvider.getUriForFile(context, "com.example.fileprovider", file)
                ImageCapture.OutputFileOptions.Builder(file).build()
            }

            3 -> {
                // Saving to a Temporary File
                val file = File.createTempFile("captured_image", ".jpg", context.cacheDir)
                ImageCapture.OutputFileOptions.Builder(file).build()
            }

            else -> throw IllegalStateException("Unexpected value: $selectedOption")
        }

        // Capture the image
        imageCapture.takePicture(
            outputOptions,
            cameraExecutor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    // Show Toast on the main thread
                    (context as ComponentActivity).runOnUiThread {
                        Toast.makeText(context, "Image saved successfully", Toast.LENGTH_SHORT)
                            .show()
                    }
                    Log.d("CameraXApp", "Image saved successfully")
                }

                override fun onError(exception: ImageCaptureException) {
                    // Show Toast on the main thread
                    (context as ComponentActivity).runOnUiThread {
                        Toast.makeText(context, "Image capture failed", Toast.LENGTH_SHORT).show()
                    }
                    Log.e("CameraXApp", "Image capture failed", exception)
                }
            }
        )
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        RadioButtonWithLabel(
            label = "Save to File",
            selected = selectedOption == 0,
            onClick = { selectedOption = 0 }
        )
        RadioButtonWithLabel(
            label = "Save to MediaStore",
            selected = selectedOption == 1,
            onClick = { selectedOption = 1 }
        )
        RadioButtonWithLabel(
            label = "Save to URI",
            selected = selectedOption == 2,
            onClick = { selectedOption = 2 }
        )
        RadioButtonWithLabel(
            label = "Save to Temporary File",
            selected = selectedOption == 3,
            onClick = { selectedOption = 3 }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                capturePhoto() // Call the function to capture the image
            }
        ) {
            Text("Capture Image")
        }
        Spacer(modifier = Modifier.height(16.dp))
        AndroidView(
            factory = { previewView },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun RadioButtonWithLabel(label: String, selected: Boolean, onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = label)
    }
}