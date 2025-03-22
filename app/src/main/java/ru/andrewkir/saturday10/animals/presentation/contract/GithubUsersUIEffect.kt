package ru.andrewkir.saturday10.animals.presentation.contract

sealed class GithubUsersUIEffect {

  data class ShowNotification(val message: String): GithubUsersUIEffect()
  data class OpenUrl(val url: String): GithubUsersUIEffect()
}