package ru.andrewkir.saturday10

import android.app.Application
import androidx.room.Room
import ru.andrewkir.saturday10.db.GoodsDatabase


class App : Application() {
  override fun onCreate() {
    super.onCreate()
    db = Room.databaseBuilder(
      applicationContext,
      GoodsDatabase::class.java,
      "goods-db"
    )
      .allowMainThreadQueries()
      .build()
  }

  companion object {
    private var db: GoodsDatabase? = null
    fun getDatabase(): GoodsDatabase? {
      return db
    }
  }
}