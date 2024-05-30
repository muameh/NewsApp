package com.mbl.deneme_newsapi.roomDatabase


import androidx.room.Database
import androidx.room.RoomDatabase
import com.mbl.deneme_newsapi.data.model.ArticleForNavigation


@Database(entities = [ArticleForNavigation::class], version = 1)
abstract class ArticleDataBase : RoomDatabase() {

    abstract fun ArticleDao() : ArticleDao

}

