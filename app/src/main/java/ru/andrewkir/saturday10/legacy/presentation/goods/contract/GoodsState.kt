package ru.andrewkir.saturday10.legacy.presentation.goods.contract

import ru.andrewkir.saturday10.legacy.data.models.GoodsItemModel
import ru.andrewkir.saturday10.legacy.data.models.UserModel

data class GoodsState(
  val goodsName: String = "",
  val goodsUrl: String = "",
  val goods: List<GoodsItemModel> = emptyList(),
  val users: List<UserModel> = emptyList()
)