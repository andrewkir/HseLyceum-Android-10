package ru.andrewkir.saturday10.presentation.goods.contract

import ru.andrewkir.saturday10.data.models.UserModel

sealed class GoodsEvent {

  data class UpdateGoodsTextField(val text: String): GoodsEvent()
  data class UpdateGoodsUrlField(val url: String): GoodsEvent()
  data class OnUserItemClick(val user: UserModel): GoodsEvent()
  data object AddButtonClicked: GoodsEvent()
}