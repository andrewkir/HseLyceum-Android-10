package ru.andrewkir.saturday10.features.savingexample

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import java.io.File


@Composable
@Destination
fun FilesNameInput() {
    var text by remember { mutableStateOf("") }
    val context = LocalContext.current
    val filename = "test.txt"

    val saveFile = {
        try {
            val file = File(context.filesDir, filename)
            file.writeText(text)
            Toast.makeText(context, "Файл сохранен!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(context, "Ошибка сохранения: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    val loadFile = {
        try {
            val file = File(context.filesDir, filename)
            if (file.exists()) {
                text = file.readText()
                Toast.makeText(context, "Файл загружен!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Файл не найден", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Ошибка загрузки: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { newValue ->
                text = newValue
            }
        )

        Spacer(Modifier.height(16.dp))

        Button(onClick = { saveFile() }) {
            Text("Сохранить")
        }

        Button(onClick = { loadFile() }) {
            Text("Загрузить")
        }

        Spacer(Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun NameInputPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        NameInput()
    }
}