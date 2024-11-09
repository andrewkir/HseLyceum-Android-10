package ru.andrewkir.saturday10.data.repository

import ru.andrewkir.saturday10.R
import ru.andrewkir.saturday10.data.models.GoodsItemModel

class GoodsRepository {

  fun getGoodsFromServer(): List<GoodsItemModel> {
    return listOf(
      GoodsItemModel(
        name = "Tovar",
        price = 123123,
        stars = 4,
        imageId = R.drawable.toilet
      ),
      GoodsItemModel(
        name = "Tovar2",
        price = 123123,
        stars = 5,
        imageId = R.drawable.ershik
      ),
      GoodsItemModel(
        name = "Tovar3",
        price = 1,
        stars = 1,
        imageId = R.drawable.ershik
      ),
    )
  }
}