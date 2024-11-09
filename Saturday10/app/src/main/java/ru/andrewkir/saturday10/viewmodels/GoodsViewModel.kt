package ru.andrewkir.saturday10.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.andrewkir.saturday10.R
import ru.andrewkir.saturday10.contract.GoodsState
import ru.andrewkir.saturday10.models.GoodsItemModel

class GoodsViewModel : ViewModel() {

  val state = MutableStateFlow(GoodsState())

  init {
    viewModelScope.launch {
      state.value = state.value.copy(
        goods = listOf(
          GoodsItemModel(
            name = "Name",
            stars = 3,
            price = 123123,
            imageId = R.drawable.ershik
          )
        )
      )
      delay(5000)
      state.value = state.value.copy(
        goods = state.value.goods?.plus(
          listOf(
            GoodsItemModel(
              name = "Name",
              stars = 3,
              price = 123123,
              imageId = R.drawable.ershik
            )
          )
        )
      )
      delay(5000)
      state.value = state.value.copy(
        goods = state.value.goods?.plus(
          listOf(
            GoodsItemModel(
              name = "Name",
              stars = 3,
              price = 123123,
              imageId = R.drawable.ershik
            )
          )
        )
      )
    }
  }
}