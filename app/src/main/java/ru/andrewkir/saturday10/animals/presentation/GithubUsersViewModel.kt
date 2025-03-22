package ru.andrewkir.saturday10.animals.presentation

import android.app.Application
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.andrewkir.saturday10.animals.data.GithubRepository
import ru.andrewkir.saturday10.animals.presentation.contract.GithubUsersUIEffect
import ru.andrewkir.saturday10.animals.presentation.contract.GithubUsersUIEvent
import ru.andrewkir.saturday10.animals.presentation.contract.GithubUsersUIEvent.OnRefreshClick
import ru.andrewkir.saturday10.animals.presentation.contract.GithubUsersUIEvent.OnUserClick
import ru.andrewkir.saturday10.animals.presentation.contract.GithubUsersUIState
import ru.andrewkir.saturday10.animals.presentation.contract.UIUser
import ru.andrewkir.saturday10.core.BaseViewModel

class GithubUsersViewModel(application: Application) : BaseViewModel<GithubUsersUIEvent, GithubUsersUIState, GithubUsersUIEffect>(
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