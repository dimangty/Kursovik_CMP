package com.example.kursovikcmp.feature.Favorites.List

import com.example.kursovikcmp.base.mvvm.BaseEvent

sealed class FavoritesListEvents: BaseEvent {
    class OnFavoriteClicked(val title: String) : FavoritesListEvents()
    class OnItemClicked(val title: String) : FavoritesListEvents()
}