package ru.andrewkir.saturday10.contract

import ru.andrewkir.saturday10.models.GoodsItemModel

data class GoodsState(
  val goods: List<GoodsItemModel>? = null
)