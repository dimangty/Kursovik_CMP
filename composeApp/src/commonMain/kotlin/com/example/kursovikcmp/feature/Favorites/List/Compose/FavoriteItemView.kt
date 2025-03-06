package com.example.kursovikcmp.feature.Favorites.List.Compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.kursovikcmp.base.ui.AppShapes
import com.example.kursovikcmp.base.ui.MyButton
import com.example.kursovikcmp.base.ui.MyText
import com.example.kursovikcmp.common.extensions.color
import com.example.kursovikcmp.feature.Favorites.List.FavoriteUiState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun FavoriteItemView(
    article: FavoriteUiState,
    onClicked: (String) -> Unit,
    onFavorite: (String) -> Unit
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .clip(AppShapes.rounded)
        .clickable { onClicked(article.title) },
        colors = CardDefaults.cardColors(
            containerColor = article.cellBackground.color(), //Card background color
            contentColor = Color.Gray  //Card content color,e.g.text
        )
    ) {
        Column(modifier = Modifier
            .padding(1.dp)
            .clip(AppShapes.rounded)) {

            AsyncImage(
                model = article.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(132.dp),
                contentScale = ContentScale.FillWidth
            )

            Column(modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    MyText(state = article.dateState)
                    MyButton(
                        modifier = Modifier,
                        onClick = { onFavorite(article.title) },
                        state = article.favoriteButton
                    )
                }
                MyText(state = article.titleState)
            }

        }
    }
}

@Preview()
@Composable
private fun PreviewArticleItemView() {
    MaterialTheme {
        FavoriteItemView(article = FavoriteUiState.getMock(),
            onClicked = {},
            onFavorite = {})
    }
}