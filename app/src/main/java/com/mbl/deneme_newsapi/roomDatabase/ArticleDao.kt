package com.mbl.deneme_newsapi.roomDatabase


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mbl.deneme_newsapi.data.model.ArticleForNavigation


@Dao
interface ArticleDao {

    @Insert
    suspend fun addArticle(articleForNavigation: ArticleForNavigation)

    @Query("SELECT * FROM favour_articles ORDER BY task_id desc ")
    suspend fun getFavourArticles() : List<ArticleForNavigation>

    @Delete
    suspend fun deleteArticle(articleForNavigation: ArticleForNavigation)


}