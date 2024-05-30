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


    suspend fun getHeadlines(countryCode:String= "us", category : String) : List<Article> {
        return apiDao.getHeadlines(countryCode,category).articles
    }

    suspend fun addArticle(articleForNavigation: ArticleForNavigation ) {
        articleDao.addArticle(articleForNavigation)
    }

    suspend fun getFavourArticles() : List<ArticleForNavigation> =
        withContext(Dispatchers.IO){
            return@withContext articleDao.getFavourArticles()
        }

    suspend fun deleteArticle(articleID: Int) {
        var article = ArticleForNavigation(articleID,"","","","","","")
        articleDao.deleteArticle(article)
    }

    suspend fun searchNews(searchQuery:String) = apiDao.searchForNews(searchQuery).articles






}