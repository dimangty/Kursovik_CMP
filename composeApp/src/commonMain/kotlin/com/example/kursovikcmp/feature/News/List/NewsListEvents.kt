package com.example.kursovikcmp.feature.News.List

import com.example.kursovikcmp.base.mvvm.BaseEvent

sealed class NewsListEvents: BaseEvent {
    class OnItemClicked(val title: String) : NewsListEvents()
}