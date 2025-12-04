package ru.andrewkir.saturday10.features.goods.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.andrewkir.saturday10.core.BaseViewModel
import ru.andrewkir.saturday10.db.Good
import ru.andrewkir.saturday10.features.goods.data.GoodsRepository
import ru.andrewkir.saturday10.features.goods.presentation.contract.GoodsUiEffect
import ru.andrewkir.saturday10.features.goods.presentation.contract.GoodsUiEvent
import ru.andrewkir.saturday10.features.goods.presentation.contract.GoodsUiState
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class GoodsViewModel @Inject constructor(
    val repository: GoodsRepository
) : BaseViewModel<GoodsUiEvent, GoodsUiState, GoodsUiEffect>(
    initialState = GoodsUiState()
) {

    init {
        getAllGoods()
    }

    override fun handleEvent(event: GoodsUiEvent) {
        when (event) {
            is GoodsUiEvent.NameTextChanged -> {
                setState { copy(nameText = event.text) }
            }

            is GoodsUiEvent.DescriptionTextChanged -> {
                setState { copy(descriptionText = event.text) }
            }

            GoodsUiEvent.OnButtonClicked -> addGood()
        }
    }

    private fun addGood() {
        repository.insertGood(
            Good(
                name = currentState.nameText,
                description = currentState.descriptionText,
                rating = Random.nextInt(1, 6)
            )
        )

        setState { copy(nameText = "", descriptionText = "") }
        getAllGoods()
    }

    private fun getAllGoods() {
        val goods = repository.getAllGoods()
        setState { copy(goodsList = goods) }
    }
}