package ru.andrewkir.saturday10.animals.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.andrewkir.saturday10.animals.presentation.contract.AnimalsUIEvent
import ru.andrewkir.saturday10.animals.presentation.contract.AnimalsUIEvent.OnAddButtonClicked
import ru.andrewkir.saturday10.animals.presentation.contract.AnimalsUIEvent.OnNameInputChanged
import ru.andrewkir.saturday10.animals.presentation.contract.AnimalsUIState

class AnimalsViewModel : ViewModel() {

  private val mutableState = MutableStateFlow(AnimalsUIState())
  val state: StateFlow<AnimalsUIState>
    get() = mutableState.asStateFlow()

  private val currentState: AnimalsUIState
    get() = mutableState.value

  fun onEvent(event: AnimalsUIEvent) {
    when (event) {
      OnAddButtonClicked -> {
        if (currentState.nameInput.isNotBlank()) {
          setState {
            copy(
              names = currentState.names + currentState.nameInput,
              nameInput = ""
            )
          }
        }
      }

      is OnNameInputChanged -> {
        setState { copy(nameInput = event.text) }
      }
    }
  }

  private fun setState(reduce: AnimalsUIState.() -> AnimalsUIState) {
    val newState = state.value.reduce()
    mutableState.value = newState
  }
}