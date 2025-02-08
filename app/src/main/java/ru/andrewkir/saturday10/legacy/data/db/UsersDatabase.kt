package ru.andrewkir.saturday10.legacy.data.db

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {
  abstract fun userDao(): UserDao
}

@Entity
data class User(
  @PrimaryKey
  val id: String,
  val login: String,
)

@Dao
interface UserDao {
  @Query("SELECT * FROM user")
  fun getAll(): List<User>

  @Insert
  fun insert(user: User)

  @Insert
  fun insertAll(users: List<User>)

  @Delete
  fun delete(user: User)
}