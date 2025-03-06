package com.example.kursovikcmp.feature.News.Details.Compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.kursovikcmp.base.ui.AppShapes
import com.example.kursovikcmp.base.ui.BaseScreen
import com.example.kursovikcmp.base.ui.MyButton
import com.example.kursovikcmp.base.ui.MyText
import com.example.kursovikcmp.base.ui.VSpacer
import com.example.kursovikcmp.common.view.Toolbar
import com.example.kursovikcmp.feature.News.Details.NewsDetailsEvents
import com.example.kursovikcmp.feature.News.Details.NewsDetailsState
import com.example.kursovikcmp.feature.News.Details.NewsDetailsViewModel
import com.example.kursovikcmp.getKoinInstance
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.parameter.parametersOf
import org.koin.mp.KoinPlatform.getKoin

@Composable
fun NewsDetailsScreen(title: String) {
    val viewModel: NewsDetailsViewModel  by getKoin().inject(parameters = { parametersOf(title) })
    val state by viewModel.flowState.collectAsState()
    val lceState by viewModel.lceState.collectAsState()

    BaseScreen(
        lceState = lceState,
        onDefaultUiEvent = viewModel::onDefaultUiEvent
    ) {
        NewsDetailsView(
            state = state,
            onUiEvent = viewModel::pushEvent
        )
    }
}

@Composable
fun NewsDetailsView(
    state: NewsDetailsState,
    onUiEvent: (NewsDetailsEvents) -> Unit
) {
    Column {
        Toolbar(toolbarState = state.titleBarState)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column() {
                state.imageUrl?.let { imageUrl ->
                    Column(modifier = Modifier.clip(AppShapes.rounded)) {
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(240.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                VSpacer(16.dp)
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        MyText(state = state.dateState)
                        MyButton(
                            modifier = Modifier,
                            onClick = { onUiEvent(NewsDetailsEvents.OnFavoriteClicked) },
                            state = state.favoriteButton
                        )
                    }

                    MyText(state = state.titleState, maxLines = 3)
                    MyText(state = state.textState, maxLines = 10)
                }

            }

            MyButton(state = state.openButton) {
                onUiEvent(NewsDetailsEvents.OnOpenClicked)
            }
        }
    }
}

@Preview()
@Composable
private fun PreviewNewsDetailsView() {
    MaterialTheme {
        NewsDetailsView(state = NewsDetailsState.getMock()) { }
    }
}