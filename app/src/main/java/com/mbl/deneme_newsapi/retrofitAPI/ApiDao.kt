package com.mbl.deneme_newsapi.retrofitAPI

import com.mbl.deneme_newsapi.data.model.NewsRESPONSE
import com.mbl.deneme_newsapi.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiDao {



    @GET("v2/top-headlines")
    suspend fun getHeadlines(
        @Query("country")countryCode : String= "us",
        @Query("category")category : String = "general",
        @Query("pageSize")pageSize : Int = 100,
        @Query("apiKey")apiKey : String = Constants.API_KEY
    ) :  NewsRESPONSE

    @GET("v2/everything")  //for SEARCH
    suspend fun searchForNews(
        @Query("q")searchQuery : String,
        //@Query("page")pageNumber:Int = 1,
        @Query("apiKey")apiKey : String = Constants.API_KEY
    ) : NewsRESPONSE








}