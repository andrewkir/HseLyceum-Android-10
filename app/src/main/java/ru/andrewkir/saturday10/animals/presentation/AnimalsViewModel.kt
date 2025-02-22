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
import ru.andrewkir.saturday10.App
import ru.andrewkir.saturday10.animals.presentation.contract.AnimalsUIEffect
import ru.andrewkir.saturday10.animals.presentation.contract.AnimalsUIEffect.ShowNotification
import ru.andrewkir.saturday10.animals.presentation.contract.AnimalsUIEvent
import ru.andrewkir.saturday10.animals.presentation.contract.AnimalsUIEvent.OnAddButtonClicked
import ru.andrewkir.saturday10.animals.presentation.contract.AnimalsUIEvent.OnNameInputChanged
import ru.andrewkir.saturday10.animals.presentation.contract.AnimalsUIEvent.OnSurnameInputChanged
import ru.andrewkir.saturday10.animals.presentation.contract.AnimalsUIState
import ru.andrewkir.saturday10.animals.presentation.contract.UIUser
import ru.andrewkir.saturday10.db.User
import java.util.UUID

class AnimalsViewModel(application: Application) : AndroidViewModel(application) {

  private val prefs = application.baseContext.getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE)

  private val mutableState = MutableStateFlow(AnimalsUIState())
  val state: StateFlow<AnimalsUIState>
    get() = mutableState.asStateFlow()

  private val _effect = Channel<AnimalsUIEffect>()
  val effect = _effect.receiveAsFlow()

  private val currentState: AnimalsUIState
    get() = mutableState.value

  init {
    val name = getName() ?: ""
    setState { copy(nameInput = name) }
    getUsers()
  }

  fun onEvent(event: AnimalsUIEvent) {
    when (event) {
      OnAddButtonClicked -> {
        if (currentState.nameInput.isNotBlank()) {
          addName(currentState.nameInput, currentState.surnameInput)
          setState {
            copy(
              users = currentState.users + UIUser(currentState.nameInput, currentState.surnameInput),
              nameInput = ""
            )
          }
        } else {
          viewModelScope.launch {
            _effect.send(ShowNotification("Имя не может быть пустым"))
          }
        }
      }

      is OnNameInputChanged -> {
        setState { copy(nameInput = event.text) }
      }

      is OnSurnameInputChanged -> {
        setState { copy(surnameInput = event.text) }
      }
    }
  }

  private fun addName(name: String, surname: String) {
    saveName(currentState.nameInput)

    val db = App.getDatabase()
    db?.let {usersDatabase ->
      val dao = usersDatabase.userDao()
      dao.insert(
        User(
          id = UUID.randomUUID().toString(),
          login = name,
          name = surname
        )
      )
    }
  }

  private fun getUsers() {
    val db = App.getDatabase()
    db?.let {usersDatabase ->
      val dao = usersDatabase.userDao()
      val users = dao.getAll()
      setState { copy(users = users.map { UIUser(it.login, it.name) }) }
    }
  }

  private fun setState(reduce: AnimalsUIState.() -> AnimalsUIState) {
    val newState = state.value.reduce()
    mutableState.value = newState
  }

  private fun saveName(name: String) {
    prefs
      .edit()
      .putString(NAME_KEY, name)
      .apply()
  }

  private fun getName(): String? {
    return prefs.getString(NAME_KEY, "")
  }


  companion object {

    private val NAME_KEY = "name"
  }
}