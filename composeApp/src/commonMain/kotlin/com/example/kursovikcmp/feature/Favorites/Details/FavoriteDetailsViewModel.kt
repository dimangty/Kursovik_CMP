package com.example.kursovikcmp.feature.Favorites.Details

import com.example.kursovikcmp.base.mvvm.BaseViewModel
import com.example.kursovikcmp.common.mvvm.TitleBarState
import com.example.kursovikcmp.common.view.updateValue
import com.example.kursovikcmp.feature.Favorites.FavoritesRepository
import com.example.kursovikcmp.feature.News.List.Model.Article
import com.example.kursovikcmp.feature.News.List.Model.toDateString
import com.example.kursovikcmp.feature.News.List.Model.toEntity
import kotlinx.coroutines.launch

class FavoriteDetailsViewModel(
    private val title: String,
    private val favoritesRepository: FavoritesRepository,
) : BaseViewModel<FavoriteDetailsState, FavoriteDetailsEvents>() {
    var article: Article? = null

    override fun initToolbar() {
        var titleBar = TitleBarState.getMock()
        titleBar = titleBar.copy(
            title = titleBar.title.updateValue("Favorites"),
            isNavigateBackVisible = true,
            onDefaultUiEvent = ::onDefaultUiEvent
        )
        updateState { copy(titleBarState = titleBar) }
    }

    override fun initScreenData() {
        viewModelScope.launch {
            loadData()
        }
    }

    override fun onEvent(event: FavoriteDetailsEvents) {
        when (event) {
            FavoriteDetailsEvents.OnOpenClicked -> {
                deviceService.openUrl(article?.url ?: "")
            }
        }
    }

    suspend fun loadData() {
        val item = favoritesRepository.get(title)
        if (item != null) {
            article = item.toEntity()
            updateState {
                copy(
                    imageUrl = item.urlToImage,
                    dateState = dateState.updateValue(item.publishedAt.toDateString() ?: ""),
                    titleState = titleState.updateValue(item.title ?: ""),
                    textState = textState.updateValue(item.description ?: "")
                )
            }
        }
    }

    override fun initialState(): FavoriteDetailsState = FavoriteDetailsState.getMock()
}