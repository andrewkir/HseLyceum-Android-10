package ru.andrewkir.saturday10.features.astro.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andrewkir.saturday10.features.astro.presentation.contract.AstroUiEvent
import ru.andrewkir.saturday10.features.astro.presentation.model.AstronautUI
import ru.andrewkir.saturday10.theme.Saturday10Theme


@Composable
fun AstroScreenContent(
    astronauts: List<AstronautUI>,
    onEvent: (AstroUiEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = 32.dp)
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Текущие космонавты в космосе:"
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(astronauts) { astronaut ->
                AstronautItem(astronaut, onEvent)
            }
        }
    }
}

@Composable
private fun AstronautItem(
    astronaut: AstronautUI,
    onEvent: (AstroUiEvent) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = {
            onEvent(AstroUiEvent.OnCardClicked(astronaut.name))
        }
    ) {
        Text(modifier = Modifier.padding(8.dp), text = astronaut.name)
        Spacer(modifier = Modifier.height(8.dp))
        Text(modifier = Modifier.padding(8.dp), text = astronaut.craft)
    }
}

@Composable
@Preview
private fun AstroScreenContentPreview() {
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
            ),
            {}
        )
    }
}