package ru.andrewkir.saturday10.animals.presentation.contract

import ru.andrewkir.saturday10.core.UIState

data class GithubUsersUIState(
  val users: List<UIUser> = emptyList(),
  val isLoading: Boolean = false
): UIState

data class UIUser(
  val id: String,
  val login: String,
  val avatarUrl:String,
  val url: String
)