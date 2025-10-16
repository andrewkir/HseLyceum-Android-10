package ru.andrewkir.saturday10.features.astro.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.andrewkir.saturday10.core.BaseViewModel
import ru.andrewkir.saturday10.features.astro.data.AstroRepository
import ru.andrewkir.saturday10.features.astro.presentation.contract.AstroUiEffect
import ru.andrewkir.saturday10.features.astro.presentation.contract.AstroUiEvent
import ru.andrewkir.saturday10.features.astro.presentation.contract.AstroUiState
import ru.andrewkir.saturday10.features.astro.presentation.model.AstronautUI

class AstroViewModel : BaseViewModel<AstroUiEvent, AstroUiState, AstroUiEffect>(
    AstroUiState(emptyList())
) {

    val repository: AstroRepository = AstroRepository()

    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            val response = repository.getAstronauts()
            response
                .onSuccess { astroResponse ->
                    setState {
                        copy(
                            astronauts = astroResponse.astronauts.map { astronaut ->
                                AstronautUI(
                                    craft = astronaut.craft,
                                    name = astronaut.name
                                )
                            }
                        )
                    }
                }
                .onFailure { e ->
                    e.printStackTrace()
                }
        }
    }

    override fun handleEvent(event: AstroUiEvent) {
        when (event) {
            is AstroUiEvent.OnCardClicked -> {
                setEffect(AstroUiEffect.NavigateToDetails(event.name))
            }
        }
    }
}