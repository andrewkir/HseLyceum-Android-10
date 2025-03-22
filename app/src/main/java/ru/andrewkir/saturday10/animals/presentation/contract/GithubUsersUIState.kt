package ru.andrewkir.saturday10.animals.presentation.contract

data class AnimalsUIState(
  val users: List<UIUser> = emptyList(),
  val isLoading: Boolean = false
)

data class UIUser(
  val id: String,
  val login: String,
  val avatarUrl:String,
  val url: String
)