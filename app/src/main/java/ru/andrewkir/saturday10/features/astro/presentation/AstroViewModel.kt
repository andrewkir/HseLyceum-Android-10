package ru.andrewkir.saturday10.features.astro.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.andrewkir.saturday10.core.BaseViewModel
import ru.andrewkir.saturday10.features.astro.presentation.contract.AstroUiEffect
import ru.andrewkir.saturday10.features.astro.presentation.contract.AstroUiEvent
import ru.andrewkir.saturday10.features.astro.presentation.contract.AstroUiState
import ru.andrewkir.saturday10.features.github.data.GithubRepository
import ru.andrewkir.saturday10.features.github.presentation.contract.GithubUsersUIEffect
import ru.andrewkir.saturday10.features.github.presentation.contract.GithubUsersUIEvent
import ru.andrewkir.saturday10.features.github.presentation.contract.GithubUsersUIEvent.OnRefreshClick
import ru.andrewkir.saturday10.features.github.presentation.contract.GithubUsersUIEvent.OnUserClick
import ru.andrewkir.saturday10.features.github.presentation.contract.GithubUsersUIState
import ru.andrewkir.saturday10.features.github.presentation.contract.UIUser

class AstroViewModel : BaseViewModel<AstroUiEvent, AstroUiState, AstroUiEffect>(
  AstroUiState(emptyList())
) {

  override fun handleEvent(event: AstroUiEvent) {
    TODO("Not yet implemented")
  }
}