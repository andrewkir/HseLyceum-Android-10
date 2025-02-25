package ru.andrewkir.saturday10

import android.app.Application
import androidx.room.Room
import ru.andrewkir.saturday10.db.UsersDatabase


class App : Application() {
  override fun onCreate() {
    super.onCreate()
    db = Room.databaseBuilder(
      applicationContext,
      UsersDatabase::class.java,
      "user-db"
    )
      .allowMainThreadQueries()
      .build()
  }

  companion object {
    private var db: UsersDatabase? = null
    fun getDatabase(): UsersDatabase? {
      return db
    }
  }
}