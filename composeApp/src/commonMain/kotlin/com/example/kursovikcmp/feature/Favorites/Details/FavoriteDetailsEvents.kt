package com.example.kursovikcmp.feature.Favorites.Details

import com.example.kursovikcmp.base.mvvm.BaseEvent

sealed class FavoriteDetailsEvents: BaseEvent {
    data object OnOpenClicked : FavoriteDetailsEvents()
}