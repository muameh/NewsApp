package com.mbl.deneme_newsapi.data.repository

import androidx.lifecycle.MutableLiveData
import com.mbl.deneme_newsapi.data.model.Article
import com.mbl.deneme_newsapi.data.model.ArticleForNavigation
import com.mbl.deneme_newsapi.data.model.NewsRESPONSE
import com.mbl.deneme_newsapi.retrofitAPI.ApiDao
import com.mbl.deneme_newsapi.roomDatabase.ArticleDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository(var articleDao: ArticleDao, var apiDao: ApiDao) {


    suspend fun getHeadlines(countryCode: String = "us", category: String): List<Article> {
        return withContext(Dispatchers.IO) {
            apiDao.getHeadlines(countryCode, category).articles
        }
    }

    suspend fun addArticle(articleForNavigation: ArticleForNavigation) {
        withContext(Dispatchers.IO) {
            articleDao.addArticle(articleForNavigation)
        }
    }

    suspend fun getFavourArticles() : List<ArticleForNavigation> {
        return withContext(Dispatchers.IO){
            articleDao.getFavourArticles()
        }
    }

    suspend fun deleteArticle(articleID: Int) {
        withContext(Dispatchers.IO){
            var article = ArticleForNavigation(articleID,"","","","","","")
            articleDao.deleteArticle(article)
        }
    }

    suspend fun searchNews(searchQuery: String): List<Article> {
        return withContext(Dispatchers.IO) {
            apiDao.searchForNews(searchQuery).articles
        }
    }
    //suspend fun searchNews(searchQuery:String) = apiDao.searchForNews(searchQuery).articles


}