package com.example.kursovikcmp.feature.Favorites.List

import com.example.kursovikcmp.base.mvvm.BaseViewModel
import com.example.kursovikcmp.common.mvvm.ErrorState
import com.example.kursovikcmp.common.mvvm.TitleBarState
import com.example.kursovikcmp.common.view.updateValue
import com.example.kursovikcmp.feature.Favorites.FavoritesRepository
import com.example.kursovikcmp.feature.News.List.Model.Article
import com.example.kursovikcmp.feature.News.List.Model.toDateString
import com.example.kursovikcmp.navigation.NavigationAction
import kotlinx.coroutines.launch

class FavoritesListViewModel(private val favoritesRepository: FavoritesRepository) :
    BaseViewModel<FavoritesListState, FavoritesListEvents>() {
    var favorites: MutableList<Article> = mutableListOf()

    override fun initScreenData() {
        loadFavoriteNews()
    }

    override fun onScreenResumed() {
        loadFavoriteNews()
    }

    override fun initToolbar() {
        var titleBar = TitleBarState.getMock()
        titleBar = titleBar.copy(
            title = titleBar.title.updateValue("Favorites"),
            isNavigateBackVisible = false
        )
        updateState { copy(titleBarState = titleBar) }
    }

    override fun initialState() = FavoritesListState()

    override fun onEvent(event: FavoritesListEvents) {
        when(event){
            is FavoritesListEvents.OnFavoriteClicked -> {
                viewModelScope.launch {
                    updateFavorite(event.title)
                }
            }

            is FavoritesListEvents.OnItemClicked -> {
                //navigate(NavigationAction.NavigateToFavoritesDetails(event.title))
            }
        }
    }

    private fun loadFavoriteNews() {
        favorites = favoritesRepository.getAllFlow().toMutableList()
        updateState { copy(favoritesItems = favorites.mapToUiItems()) }
    }

    private fun List<Article>.mapToUiItems(): List<FavoriteUiState> {
        var items = mutableListOf<FavoriteUiState>()
        for (item in this) {
            if (item.title != null && item.description != null) {
                items.add(
                    FavoriteUiState(
                        id = items.count().toString(),
                        title = item.title,
                        text = item.description,
                        imageUrl = item.urlToImage,
                        date = item.publishedAt.toDateString()
                    )
                )
            }
        }
        return items
    }

    private suspend fun updateFavorite(title: String) {
        val index = favorites.indexOfFirst { it.title == title }

        if (index != -1) {
            showAlert(
                ErrorState.AlertError(
                    title = "Warning",
                    message = "Do you want to delete this news?",
                    positiveButtonText = "Yes",
                    positiveAction = { viewModelScope.launch { deleteFavorite(index, title) } },
                    negativeButtonText = "No",
                    negativeAction = { hideError() }
                ))
        }
    }


    private suspend fun deleteFavorite(index: Int, title: String) {
        favorites.removeAt(index)
        favoritesRepository.delete(title)
        updateState { copy(favoritesItems = favorites.mapToUiItems()) }
        hideError()
    }
}