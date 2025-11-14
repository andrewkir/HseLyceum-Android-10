package ru.andrewkir.saturday10.features.goods.data

import ru.andrewkir.saturday10.db.Good
import ru.andrewkir.saturday10.db.GoodDao

class GoodsRepository(
    private val goodDao: GoodDao?
) {

    fun insertGood(good: Good) {
        goodDao?.insert(good)
    }

    fun getAllGoods(): List<Good> {
        return goodDao?.getAllGoods() ?: emptyList()
    }
}