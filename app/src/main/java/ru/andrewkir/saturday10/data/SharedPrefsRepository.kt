package ru.andrewkir.saturday10.data

import android.content.Context
import android.content.SharedPreferences

class SharedPrefsRepository(
  context: Context,
) {

  private val prefs: SharedPreferences =
    context.getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE)

  var username: String
    get() {
      return prefs.getString(USERNAME_KEY, "") ?: ""
    }
    set(value) {
      prefs.edit().putString(USERNAME_KEY, value).apply()
    }
  

  companion object {
    private const val USERNAME_KEY = "USERNAME"
  }
}