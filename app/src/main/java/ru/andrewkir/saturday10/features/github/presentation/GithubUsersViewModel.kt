package ru.andrewkir.saturday10.features.github.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.andrewkir.saturday10.core.BaseViewModel
import ru.andrewkir.saturday10.features.github.data.GithubRepository
import ru.andrewkir.saturday10.features.github.presentation.contract.GithubUsersUIEffect
import ru.andrewkir.saturday10.features.github.presentation.contract.GithubUsersUIEvent
import ru.andrewkir.saturday10.features.github.presentation.contract.GithubUsersUIEvent.OnRefreshClick
import ru.andrewkir.saturday10.features.github.presentation.contract.GithubUsersUIEvent.OnUserClick
import ru.andrewkir.saturday10.features.github.presentation.contract.GithubUsersUIState
import ru.andrewkir.saturday10.features.github.presentation.contract.UIUser

class GithubUsersViewModel : BaseViewModel<GithubUsersUIEvent, GithubUsersUIState, GithubUsersUIEffect>(
  GithubUsersUIState()
) {
  private val repo = GithubRepository()

  init {
    loadUsers()
  }

  override fun handleEvent(event: GithubUsersUIEvent) {
    when (event) {
      OnRefreshClick -> {
        loadUsers()
      }

      is OnUserClick -> {
        viewModelScope.launch {
          setEffect(GithubUsersUIEffect.OpenUrl(event.url))
        }
      }
    }
  }

  private fun loadUsers() {
    viewModelScope.launch {
      setState { copy(isLoading = true) }
      repo.getUsers()
        .onSuccess { usersResult ->
          val users = usersResult.map { user ->
            UIUser(
              login = user.login,
              url = user.url,
              id = user.id.toString(),
              avatarUrl = user.avatarUrl
            )
          }
          setState { copy(users = users) }
        }
        .onFailure {
          setEffect(GithubUsersUIEffect.ShowNotification(it.message ?: "Ошибка загрузки"))
        }
      setState { copy(isLoading = false) }
    }
  }
}