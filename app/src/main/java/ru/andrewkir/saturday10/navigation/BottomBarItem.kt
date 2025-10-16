package ru.andrewkir.saturday10.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import ru.andrewkir.saturday10.features.destinations.AstroScreenDestination
import ru.andrewkir.saturday10.features.destinations.GithubScreenDestination

enum class BottomBarItem(
    val label: String = "",
    val icon: ImageVector = Icons.Filled.Home,
    val direction: DirectionDestinationSpec,
) {
    ASTRO(
        label = "Astro",
        icon = Icons.Filled.Home,
        direction = AstroScreenDestination,
    ),

    GITHUB(
        label = "Github",
        icon = Icons.Filled.AccountBox,
        direction = GithubScreenDestination,
    ),
}