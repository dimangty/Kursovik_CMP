package com.example.kursovikcmp.DB

import com.example.kursovikcmp.Database
import com.example.kursovikcmp.database.ArticleDb
import com.example.kursovikcmp.feature.News.List.Model.Article
import com.example.kursovikcmp.feature.News.List.Model.toDb
import com.example.kursovikcmp.feature.News.List.Model.toEntity
import kotlin.coroutines.CoroutineContext

class ArticleDao(
    private val db: Database,
    private val coroutineContext: CoroutineContext
) {
    private val articleQueries = db.articleDbQueries

    fun getAll(): List<Article> =
        articleQueries
            .getAll()
            .executeAsList()
            .map { it.toEntity() }

    suspend fun insert(article: Article) = articleQueries.insert(article.toDb())
    suspend fun delete(title: String) = articleQueries.delete(title = title)
    suspend fun check(title: String): Boolean =
        articleQueries.get(title = title).executeAsOneOrNull() != null

    suspend fun get(title: String): ArticleDb? =
        articleQueries.get(title = title).executeAsOneOrNull()
}
