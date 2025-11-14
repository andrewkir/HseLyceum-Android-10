package ru.andrewkir.saturday10.features.goods.presentation.contract

import ru.andrewkir.saturday10.core.UIState
import ru.andrewkir.saturday10.db.Good

data class GoodsUiState(
    val nameText: String = "",
    val descriptionText: String = "",
    val goodsList: List<Good> = emptyList()
) : UIState
