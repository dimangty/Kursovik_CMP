package com.example.kursovikcmp.feature.News.Details

import com.example.kursovikcmp.base.mvvm.BaseEvent

sealed class NewsDetailsEvents: BaseEvent {
    data object OnFavoriteClicked : NewsDetailsEvents()
    data object OnOpenClicked : NewsDetailsEvents()
}