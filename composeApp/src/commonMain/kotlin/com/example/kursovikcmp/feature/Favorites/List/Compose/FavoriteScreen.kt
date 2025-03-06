package com.example.kursovikcmp.feature.Favorites.List.Compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kursovikcmp.base.ui.BaseScreen
import com.example.kursovikcmp.base.ui.VSpacer
import com.example.kursovikcmp.common.extensions.color
import com.example.kursovikcmp.common.view.Toolbar
import com.example.kursovikcmp.feature.Favorites.List.FavoritesListEvents
import com.example.kursovikcmp.feature.Favorites.List.FavoritesListState
import com.example.kursovikcmp.feature.Favorites.List.FavoritesListViewModel
import com.example.kursovikcmp.getKoinInstance
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun FavoriteScreen() {
    val viewModel: FavoritesListViewModel = getKoinInstance()
    val state by viewModel.flowState.collectAsState()
    val lceState by viewModel.lceState.collectAsState()

    BaseScreen(lceState = lceState,
        onDefaultUiEvent = viewModel::onDefaultUiEvent) {
        FavoriteScreenView(state = state,
            onUiEvent = viewModel::pushEvent
        )
    }
}


@Composable
fun FavoriteScreenView(
    state: FavoritesListState,
    onUiEvent: (FavoritesListEvents) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(state.backGroundColor.color())) {
        Toolbar(toolbarState = state.titleBarState)
        Column(
            modifier = Modifier.fillMaxSize().background(state.backGroundColor.color()),
            verticalArrangement = Arrangement.SpaceBetween) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
            ) {
                items(state.favoritesItems.size) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        FavoriteItemView(article = state.favoritesItems[it],
                            onClicked = { title ->
                                onUiEvent(
                                    FavoritesListEvents.OnItemClicked(
                                        title
                                    )
                                )
                            },
                            onFavorite = { title ->
                                onUiEvent(
                                    FavoritesListEvents.OnFavoriteClicked(
                                        title
                                    )
                                )
                            })
                    }
                }
            }

            VSpacer(10.dp)
        }
    }


}

@Preview()
@Composable
private fun PreviewNewsScreenView() {
    MaterialTheme {
        FavoriteScreenView(state = FavoritesListState.getMock(),
            onUiEvent = {})
    }
}