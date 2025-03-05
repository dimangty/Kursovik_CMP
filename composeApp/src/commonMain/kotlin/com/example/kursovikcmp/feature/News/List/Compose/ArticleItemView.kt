package com.example.kursovikcmp.feature.News.List.Compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
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
import com.example.kursovikcmp.base.ui.VSpacer
import com.example.kursovikcmp.feature.News.List.NewsUiState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ArticleItemView(article: NewsUiState,
                    onClicked: (String) -> Unit,
                    onFavorite: (String) -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .clip(AppShapes.rounded)
        .clickable { onClicked(article.title) },
        contentColor = Color.Gray,
    ) {
        Column (modifier = Modifier) {
            article.imageUrl?.let { imageUrl ->

                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(132.dp),
                    contentScale = ContentScale.FillWidth
                )
            }
            Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
                Row( modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    MyText(state = article.dateState)
                    MyButton(modifier = Modifier,
                        onClick = {onFavorite(article.title)},
                        state = article.favoriteButton)
                }
                MyText(state = article.titleState)
                VSpacer(8.dp)
                MyText(state = article.textState)
            }

        }
    }
}

@Preview()
@Composable
private fun PreviewArticleItemView() {
    MaterialTheme {
        ArticleItemView(article = NewsUiState.getMock(),
            onClicked = {},
            onFavorite = {})
    }
}