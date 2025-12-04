package ru.andrewkir.saturday10.features.goods.data

import ru.andrewkir.saturday10.db.Good
import ru.andrewkir.saturday10.db.GoodDao
import ru.andrewkir.saturday10.db.GoodsDatabase
import javax.inject.Inject

class GoodsRepository @Inject constructor(
    goodsDatabase: GoodsDatabase,
) {
    private val goodDao: GoodDao = goodsDatabase.goodsDao()

    fun insertGood(good: Good) {
        goodDao.insert(good)
    }

    fun getAllGoods(): List<Good> {
        return goodDao.getAllGoods()
    }
}