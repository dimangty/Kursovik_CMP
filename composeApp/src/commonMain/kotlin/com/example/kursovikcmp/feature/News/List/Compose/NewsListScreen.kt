package com.example.kursovikcmp.feature.News.List.Compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kursovikcmp.base.ui.BaseScreen
import com.example.kursovikcmp.base.ui.VSpacer
import com.example.kursovikcmp.common.extensions.color
import com.example.kursovikcmp.common.view.Toolbar
import com.example.kursovikcmp.feature.News.List.NewsListEvents
import com.example.kursovikcmp.feature.News.List.NewsListState
import com.example.kursovikcmp.feature.News.List.NewsListViewModel
import com.example.kursovikcmp.getKoinInstance
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NewsScreen() {
    val viewModel: NewsListViewModel = getKoinInstance()
    val state by viewModel.flowState.collectAsState()
    val lceState by viewModel.lceState.collectAsState()

    BaseScreen(lceState = lceState,
        onDefaultUiEvent = viewModel::onDefaultUiEvent) {
        NewsScreenView(
            state = state,
            onUiEvent = viewModel::pushEvent)
    }
}


@Composable
fun NewsScreenView(state: NewsListState,
                   onUiEvent: (NewsListEvents) -> Unit) {
    Column(modifier = Modifier.background(color = state.backGroundColor.color())) {
        Toolbar(toolbarState = state.titleBarState)
        LazyColumn(modifier = Modifier.padding(horizontal = 8.dp)) {
            item { VSpacer(8.dp) }
            items(state.newsItems.count()) { index ->
                val article = state.newsItems[index]
                ArticleItemView(article = article,
                    onClicked = {
                        onUiEvent(NewsListEvents.OnItemClicked(article.title))
                    },
                    onFavorite = {
                        onUiEvent(NewsListEvents.OnFavoriteClicked(article.title))
                    })
                VSpacer(8.dp)
            }
        }
    }

}

@Preview()
@Composable
private fun PreviewNewsScreenView() {
    MaterialTheme {
        NewsScreenView(state = NewsListState.getMock(),
            onUiEvent = {})
    }
}