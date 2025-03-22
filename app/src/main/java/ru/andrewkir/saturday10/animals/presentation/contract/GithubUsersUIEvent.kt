package ru.andrewkir.saturday10.animals.presentation.contract

import ru.andrewkir.saturday10.core.UIEvent

sealed class GithubUsersUIEvent: UIEvent {
  data object OnRefreshClick: GithubUsersUIEvent()
  data class OnUserClick(val url: String): GithubUsersUIEvent()
}