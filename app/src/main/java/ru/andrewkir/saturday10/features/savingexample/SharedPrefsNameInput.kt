package ru.andrewkir.saturday10.features.savingexample

import android.content.Context
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


@Composable
@Destination
fun NameInput() {
    var text by remember { mutableStateOf("") }
    val context = LocalContext.current
    val sharedPreferences = remember {
        context.getSharedPreferences("name_prefs", Context.MODE_PRIVATE)
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

        Button(onClick = {
            sharedPreferences
                .edit()
                .putString("NAME", text)
                .commit()

            Toast.makeText(context, "Успешно сохранено", Toast.LENGTH_SHORT).show()
        }) {
            Text("Сохранить")
        }

        Spacer(Modifier.height(16.dp))

        sharedPreferences.getString("NAME", "")?.let { stringFromSharedPrefs ->
            Text(
                text = stringFromSharedPrefs
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NameInputPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        NameInput()
    }
}