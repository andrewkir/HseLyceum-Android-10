package ru.andrewkir.saturday10.animals.presentation.contract

sealed class GithubUsersUIEvent {
  data object OnRefreshClick: GithubUsersUIEvent()
}