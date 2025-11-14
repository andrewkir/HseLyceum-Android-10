package ru.andrewkir.saturday10.db

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase


@Database(
    entities = [Good::class],
    version = 1
)
abstract class GoodsDatabase : RoomDatabase() {
    abstract fun goodsDao(): GoodDao
}

@Entity
data class Good(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val rating: Int,
)

@Dao
interface GoodDao {

    @Query("SELECT * FROM Good")
    fun getAllGoods(): List<Good>

    @Insert
    fun insert(good: Good)

    @Insert
    fun insertAll(goods: List<Good>)

    @Delete
    fun delete(good: Good)
}