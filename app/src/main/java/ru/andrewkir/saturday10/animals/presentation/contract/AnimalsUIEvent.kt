package ru.andrewkir.saturday10.animals.presentation.contract

sealed class AnimalsUIEvent {
  data object OnAddButtonClicked : AnimalsUIEvent()
  data class OnNameInputChanged(val text: String) : AnimalsUIEvent()
  data class OnSurnameInputChanged(val text: String) : AnimalsUIEvent()
}