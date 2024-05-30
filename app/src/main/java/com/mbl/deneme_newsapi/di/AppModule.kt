package com.mbl.deneme_newsapi.di

import android.content.Context
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import com.mbl.deneme_newsapi.data.repository.NewsRepository
import com.mbl.deneme_newsapi.retrofitAPI.ApiDao
import com.mbl.deneme_newsapi.retrofitAPI.ApiUtils
import com.mbl.deneme_newsapi.roomDatabase.ArticleDao
import com.mbl.deneme_newsapi.roomDatabase.ArticleDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideArticleDao(@ApplicationContext context: Context): ArticleDao {
        val db = databaseBuilder(
            context.applicationContext,
            ArticleDataBase::class.java,
            "article.db")
            .build()
        return db.ArticleDao()
    }

    @Provides
    @Singleton
    fun provideApiDao () : ApiDao {
        return ApiUtils.getDao()
    }

    @Provides
    @Singleton
    fun provideRepository(articleDao: ArticleDao,apiDao: ApiDao) : NewsRepository {
        return NewsRepository(articleDao,apiDao)
    }




}

