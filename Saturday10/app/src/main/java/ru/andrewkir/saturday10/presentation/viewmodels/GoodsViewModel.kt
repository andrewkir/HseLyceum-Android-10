package ru.andrewkir.saturday10.presentation.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import ru.andrewkir.saturday10.data.repository.GoodsRepository
import ru.andrewkir.saturday10.presentation.contract.GoodsEvent
import ru.andrewkir.saturday10.presentation.contract.GoodsEvent.UpdateGoodsTextField
import ru.andrewkir.saturday10.presentation.contract.GoodsState

class GoodsViewModel : ViewModel() {

  val state = MutableStateFlow(GoodsState())
  private val repo = GoodsRepository()

  init {
    val goods = repo.getGoodsFromServer()
    state.value = state.value.copy(goods = goods)
  }

  fun handleEvent(event: GoodsEvent) {
    when (event) {
      is UpdateGoodsTextField -> {
        state.value = state.value.copy(goodsName = event.text)
      }
    }
  }
}