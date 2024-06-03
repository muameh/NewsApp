package com.mbl.deneme_newsapi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbl.deneme_newsapi.data.model.Article
import com.mbl.deneme_newsapi.data.model.ArticleForNavigation
import com.mbl.deneme_newsapi.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {
    var headLines = MutableLiveData<List<Article>>()
    var searchList = MutableLiveData<List<Article>>()
    var favourArticles = MutableLiveData<List<ArticleForNavigation>>()

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun getHeadlines(countryCode: String,category:String="general") = viewModelScope.launch(Dispatchers.IO) {
        try {
            headLines.postValue(newsRepository.getHeadlines(countryCode,category))
        } catch (e : Exception){
            _error.postValue("Failed Code: ${e.message}")
        }
    }

    fun searchNews(searchQuery:String) = viewModelScope.launch(Dispatchers.IO){
        try {
            searchList.postValue(newsRepository.searchNews(searchQuery))
        } catch (e : Exception) {
            _error.postValue("Failed Code: ${e.message}")
        }
    }

    fun addArticle(articleForNavigation: ArticleForNavigation ) {
        viewModelScope.launch (Dispatchers.IO) {
            newsRepository.addArticle(articleForNavigation)
        }
    }

    fun getfavourArticles() {
        viewModelScope.launch (Dispatchers.IO){
            //favourArticles.value = newsRepository.getFavourArticles()
            favourArticles.postValue(newsRepository.getFavourArticles())
        }
    }

    fun deleteArticle(articleID : Int) {
        viewModelScope.launch (Dispatchers.IO) {
            newsRepository.deleteArticle(articleID)
            getfavourArticles()
        }
    }

}

