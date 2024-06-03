package com.mbl.deneme_newsapi.retrofitAPI

import com.mbl.deneme_newsapi.utils.Constants


class ApiUtils {

    companion object {

        val BASE_URL =" https://newsapi.org/"

        fun getDao(): ApiDao {
            return RetrofitClient.getClient(BASE_URL).create(ApiDao::class.java)
        }
    }
}