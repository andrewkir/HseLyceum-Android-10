package ru.andrewkir.saturday10.features.goods.presentation.contract

import ru.andrewkir.saturday10.core.UIEvent

sealed interface GoodsUiEvent : UIEvent {
    data class NameTextChanged(val text: String) : GoodsUiEvent
    data class DescriptionTextChanged(val text: String) : GoodsUiEvent

    data object OnButtonClicked : GoodsUiEvent
}