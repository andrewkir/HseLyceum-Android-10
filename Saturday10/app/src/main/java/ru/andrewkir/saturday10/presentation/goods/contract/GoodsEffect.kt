package ru.andrewkir.saturday10.presentation.goods.contract

sealed interface GoodsEffect {

  data object OpenDetails: GoodsEffect
}