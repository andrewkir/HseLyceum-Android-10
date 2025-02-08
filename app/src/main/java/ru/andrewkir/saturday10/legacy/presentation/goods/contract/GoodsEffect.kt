package ru.andrewkir.saturday10.legacy.presentation.goods.contract

import ru.andrewkir.saturday10.legacy.data.models.UserModel

sealed interface GoodsEffect {

  data class OpenDetails(val item: UserModel): GoodsEffect
}