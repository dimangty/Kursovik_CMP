package com.example.kursovikcmp.base.ui.BottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val label : String = "",
    val icon : ImageVector = Icons.Filled.Home,
    val route : String = "",
) {
    fun isSelected(destination : String?) : Boolean {
        if (destination == route) {
            return true
        }

        return false
    }

    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "News",
                icon = Icons.Filled.Home,
                route = Screens.Home.route,
            ),
            BottomNavigationItem(
                label = "Favorites",
                icon = Icons.Filled.Favorite,
                route = Screens.Favorites.route
            )
        )
    }
}

sealed class Screens(val route : String) {
    object Home : Screens("home_screen")
    object Favorites : Screens("favorites_screen")
}