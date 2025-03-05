package com.example.kursovikcmp.feature.News.List

import com.example.kursovikcmp.Network.ApiErrorWrapper
import com.example.kursovikcmp.base.mvvm.BaseViewModel
import com.example.kursovikcmp.common.view.updateValue
import com.example.kursovikcmp.feature.News.List.Model.Article
import com.example.kursovikcmp.feature.News.List.Model.NewsList
import com.example.kursovikcmp.feature.News.List.Model.toDateString
import com.example.kursovikcmp.feature.News.NewsService
import io.ktor.client.call.body
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NewsListViewModel(private val newsService: NewsService): BaseViewModel<NewsListState, NewsListEvents>() {

    var favorites: MutableList<Article> = mutableListOf()
    var news: MutableList<Article> = mutableListOf()

    override fun initScreenData() {
        viewModelScope.launch(
            CoroutineExceptionHandler { _, t ->
                //pushEvent(Event.Error(t.message.orEmpty()))
            }
        ) {
            loadFavoriteNews()
            loadNews()
        }
    }

    override fun onScreenResumed() {
        loadFavoriteNews()
        updateState { copy(newsItems = news.mapToUiItems()) }
    }

    override fun initToolbar() {
        var titleBar = state.titleBarState.copy()
        titleBar = titleBar.copy(title = titleBar.title.updateValue("News"),
            isNavigateBackVisible = false)
        updateState { copy(titleBarState = titleBar) }
    }

    override fun initialState() = NewsListState()

    override fun onEvent(event: NewsListEvents) {
        when(event){
            is NewsListEvents.OnItemClicked -> {
                //navigate(NavigationAction.NavigateToNewsDetails(event.title))
            }
        }
    }

    private fun loadFavoriteNews() {
        favorites = mutableListOf() //favoritesRepository.getAllFlow().toMutableList()
    }


    private suspend fun loadNews() {
        lceStateManager.showLoading()
        try {
            val response = newsService.getNews()
            lceStateManager.hideLoading()
            if (response.status.isSuccess()) {
                val regResponse = response.body<NewsList>()
                news = regResponse.articles.toMutableList()
                newsService.news = news
                updateState { copy(newsItems = news.mapToUiItems()) }
            } else {
                val error = response.body<ApiErrorWrapper>().error
                val message = error?.message ?: response.bodyAsText()
                showError(message)
            }
        } catch (t: Throwable) {
            lceStateManager.hideLoading()
            showError(t.message ?: "Error")
        }

    }

    private fun List<Article>.mapToUiItems(): List<NewsUiState> {
        var items = mutableListOf<NewsUiState>()
        for (item in this) {
            if (item.title != null && item.description != null) {
                items.add(
                    NewsUiState(
                        id = items.count().toString(),
                        title = item.title,
                        text = item.description,
                        imageUrl = item.urlToImage,
                        date = item.publishedAt.toDateString(),
                        isFavorite = checkIsFavorite(item)
                    )
                )
            }
        }
        return items
    }

    private fun checkIsFavorite(article: Article): Boolean {
        return favorites.any { it.title == article.title}
    }

    private suspend fun updateFavorite(title: String) {
//        val check = favoritesRepository.check(title)
//        if (!check) {
//            val item = news.find { it.title == title}
//            if (item != null) {
//                favoritesRepository.insert(item)
//            }
//        } else {
//            favoritesRepository.delete(title)
//        }
//
//        delay(100)
//        loadFavoriteNews()
//        updateState { copy(newsItems = news.mapToUiItems()) }
    }

}