package ru.andrewkir.saturday10.features.astro.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.flow.collectLatest
import ru.andrewkir.saturday10.features.astro.presentation.contract.AstroUiEffect

@Composable
@Destination(start = true)
fun AstroScreen(
//    navigator: DestinationsNavigator,
) {
    val viewModel: AstroViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is AstroUiEffect.NavigateToDetails -> {
//                    navigator.navigate(AstroDetailsDestination(effect.name))
                }
            }
        }
    }

    AstroScreenContent(
        astronauts = state.astronauts,
        onEvent = viewModel::setEvent
        // аналогичная запись
//            { event ->
//                viewModel.setEvent(event)
//            }
    )
}