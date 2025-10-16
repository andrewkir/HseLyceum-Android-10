package ru.andrewkir.saturday10.features.astro.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun AstroDetails(
    name: String
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = name,
            fontSize = 36.sp
        )
    }
}

@Composable
@Preview
private fun AstroDetailsPreview() {
    AstroDetails("Ivan")
}