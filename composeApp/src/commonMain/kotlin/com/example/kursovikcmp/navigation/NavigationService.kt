package com.example.kursovikcmp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NavigationService {
    private var navController: NavHostController? = null

    private val _currentDestination = MutableStateFlow("")
    val currentDestination = _currentDestination.asStateFlow()

    private val onDestinationChangedListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            _currentDestination.update {
                destination.route?.substringBefore("/") ?: ""
            }
        }

    fun setNavController(navController: NavHostController) {
        this.navController = navController
        navController.removeOnDestinationChangedListener(onDestinationChangedListener)
        navController.addOnDestinationChangedListener(onDestinationChangedListener)
    }

    fun <T> setPreviousBackStackEntry(key: String, value: T) {
        navController?.previousBackStackEntry
            ?.savedStateHandle
            ?.set(key, value)
    }

    fun <T> getCurrentBackStackEntry(key: String): T? {
        val current = navController?.currentBackStackEntry
        return if (current != null && current.savedStateHandle.contains(key)) {
            current.savedStateHandle[key]
        } else {
            null
        }
    }

    fun <T> clearCurrentBackStackEntry(key: String) {
        navController?.currentBackStackEntry?.savedStateHandle?.remove<T>(key)
    }

    @Suppress("LongMethod", "CyclomaticComplexMethod") // TODO
    fun navigate(action: NavigationAction) {
        navController?.run {
            when (action) {
                is NavigationAction.NavigateToFavoritesDetails,
                is NavigationAction.NavigateToNewsDetails,
                    -> {
                    navigate(action)
                }

                NavigationAction.NavigateBack -> {
                    navigateBack()
                }
            }
        }
    }

    fun navigateBack() {
        navController?.navigateUp()
    }
}