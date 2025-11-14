package ru.andrewkir.saturday10.features.goods.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.andrewkir.saturday10.db.Good
import ru.andrewkir.saturday10.features.goods.presentation.contract.GoodsUiEvent
import ru.andrewkir.saturday10.features.goods.presentation.contract.GoodsUiState

@Composable
fun GoodsScreenContent(
    uiState: GoodsUiState,
    onEvent: (GoodsUiEvent) -> Unit,
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.nameText,
            onValueChange = { text ->
                onEvent(GoodsUiEvent.NameTextChanged(text))
            }
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.descriptionText,
            onValueChange = { text ->
                onEvent(GoodsUiEvent.DescriptionTextChanged(text))
            }
        )
        Spacer(Modifier.height(8.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onEvent(GoodsUiEvent.OnButtonClicked)
            }
        ) {
            Text("Добавить")
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.goodsList) { good ->
                GoodRow(good)
            }
        }
    }
}

@Composable
fun GoodRow(good: Good) {
    ElevatedCard {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(good.name)
            Text(good.description)

            Row {
                for (i in 1..5) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = if (i <= good.rating) {
                            Color.Yellow
                        } else {
                            Color.LightGray
                        }
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun GoodsScreenContentPreview() {
    GoodsScreenContent(GoodsUiState(), {})
}