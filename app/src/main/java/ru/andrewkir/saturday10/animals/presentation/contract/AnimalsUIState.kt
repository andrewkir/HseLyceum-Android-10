package ru.andrewkir.saturday10.animals.presentation.contract

data class AnimalsUIState(
  val nameInput: String = "",
  val names: List<String> = emptyList(),
)