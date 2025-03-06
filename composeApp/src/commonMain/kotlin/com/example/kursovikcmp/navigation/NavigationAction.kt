package com.example.kursovikcmp.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationAction {
    @Serializable
    class NavigateToNewsDetails(val title: String) : NavigationAction()

    @Serializable
    class NavigateToFavoritesDetails(val title: String) : NavigationAction()

    @Serializable
    data object NavigateBack : NavigationAction()
}