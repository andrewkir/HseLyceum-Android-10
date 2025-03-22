package ru.andrewkir.saturday10.animals.presentation

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.andrewkir.saturday10.animals.data.GithubRepository
import ru.andrewkir.saturday10.animals.presentation.contract.AnimalsUIState
import ru.andrewkir.saturday10.animals.presentation.contract.GithubUsersUIEffect
import ru.andrewkir.saturday10.animals.presentation.contract.GithubUsersUIEvent
import ru.andrewkir.saturday10.animals.presentation.contract.GithubUsersUIEvent.OnRefreshClick
import ru.andrewkir.saturday10.animals.presentation.contract.GithubUsersUIEvent.OnUserClick
import ru.andrewkir.saturday10.animals.presentation.contract.UIUser

class GithubUsersViewModel(application: Application) : AndroidViewModel(application) {

  private val prefs = application.baseContext.getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE)

  private val mutableState = MutableStateFlow(AnimalsUIState())
  val state: StateFlow<AnimalsUIState>
    get() = mutableState.asStateFlow()

  private val _effect = Channel<GithubUsersUIEffect>()
  val effect = _effect.receiveAsFlow()

  private val currentState: AnimalsUIState
    get() = mutableState.value

  private val repo = GithubRepository()

  init {
    loadUsers()
  }

  fun onEvent(event: GithubUsersUIEvent) {
    when (event) {
      OnRefreshClick -> {
        loadUsers()
      }

      is OnUserClick -> {
        viewModelScope.launch {
          _effect.send(GithubUsersUIEffect.OpenUrl(event.url))
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
          _effect.send(GithubUsersUIEffect.ShowNotification(it.message ?: "Ошибка загрузки"))
        }
      setState { copy(isLoading = false) }
    }
  }

  private fun setState(reduce: AnimalsUIState.() -> AnimalsUIState) {
    val newState = state.value.reduce()
    mutableState.value = newState
  }
}