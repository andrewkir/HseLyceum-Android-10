package ru.andrewkir.saturday10.features.astro.presentation.contract

import ru.andrewkir.saturday10.core.UIState
import ru.andrewkir.saturday10.features.astro.presentation.model.AstronautUI

data class AstroUiState(
    val astronauts: List<AstronautUI>
): UIState
