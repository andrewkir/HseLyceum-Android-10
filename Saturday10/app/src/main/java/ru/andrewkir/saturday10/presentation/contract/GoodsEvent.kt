package ru.andrewkir.saturday10.presentation.contract

sealed class GoodsEvent {

  data class UpdateGoodsTextField(val text: String): GoodsEvent()
  data class UpdateGoodsUrlField(val url: String): GoodsEvent()
  data object AddButtonClicked: GoodsEvent()
}