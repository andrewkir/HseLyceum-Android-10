package ru.andrewkir.saturday10.features.github.presentation.contract

import ru.andrewkir.saturday10.core.UIEffect

sealed class GithubUsersUIEffect: UIEffect {

  data class ShowNotification(val message: String): GithubUsersUIEffect()
  data class OpenUrl(val url: String): GithubUsersUIEffect()
}