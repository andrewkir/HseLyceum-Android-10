package ru.andrewkir.saturday10.features.astro.presentation

import ru.andrewkir.saturday10.core.BaseViewModel
import ru.andrewkir.saturday10.features.astro.presentation.contract.AstroUiEffect
import ru.andrewkir.saturday10.features.astro.presentation.contract.AstroUiEvent
import ru.andrewkir.saturday10.features.astro.presentation.contract.AstroUiState

class AstroViewModel : BaseViewModel<AstroUiEvent, AstroUiState, AstroUiEffect>(
  AstroUiState(emptyList())
) {

  override fun handleEvent(event: AstroUiEvent) {

  }
}