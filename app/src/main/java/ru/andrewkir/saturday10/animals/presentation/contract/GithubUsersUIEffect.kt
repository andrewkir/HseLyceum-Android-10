package ru.andrewkir.saturday10.animals.presentation.contract

sealed class GithubUsersUIEffect {

  class ShowNotification(val message: String): GithubUsersUIEffect()
}