package ru.andrewkir.saturday10.features.astro.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AstroScreen() {
    val viewModel = viewModel<AstroViewModel>()
    val state by viewModel.uiState.collectAsState()

    AstroScreenContent(
        astronauts = state.astronauts,
    )
}