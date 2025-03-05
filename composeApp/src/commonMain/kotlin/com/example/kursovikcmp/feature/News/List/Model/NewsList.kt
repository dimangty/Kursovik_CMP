package com.example.kursovikcmp.feature.News.List.Model

import kotlinx.serialization.Serializable

@Serializable
data class NewsList(
    val status: String? = null,
    val totalResults: Int? = null,
    val articles: List<Article>
)