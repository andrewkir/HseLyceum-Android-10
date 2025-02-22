package ru.andrewkir.saturday10.animals.presentation.contract

data class AnimalsUIState(
  val nameInput: String = "",
  val surnameInput: String = "",
  val users: List<UIUser> = emptyList(),
)

data class UIUser(
  val name: String,
  val surname: String
)