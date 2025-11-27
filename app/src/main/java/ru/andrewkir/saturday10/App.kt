package ru.andrewkir.saturday10

import android.app.Application
import androidx.room.Room
import dagger.hilt.android.HiltAndroidApp
import ru.andrewkir.saturday10.db.GoodsDatabase


@HiltAndroidApp
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