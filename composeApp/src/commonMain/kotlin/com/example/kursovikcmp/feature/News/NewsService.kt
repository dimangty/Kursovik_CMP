package com.example.kursovikcmp.feature.News

import com.example.kursovikcmp.Network.NetworkSettings
import com.example.kursovikcmp.feature.News.List.Model.Article
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class NewsService(private val client: HttpClient,
                  private val settings: NetworkSettings
) {
    var news: MutableList<Article> = mutableListOf()
    suspend fun getNews() = client.get(settings.newsApiUrl)
}