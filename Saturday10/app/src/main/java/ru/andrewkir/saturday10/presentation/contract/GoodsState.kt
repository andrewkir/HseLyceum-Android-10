package ru.andrewkir.saturday10.presentation.contract

import ru.andrewkir.saturday10.data.models.GoodsItemModel

data class GoodsState(
  val goodsName: String = "",
  val goods: List<GoodsItemModel> = emptyList()
)