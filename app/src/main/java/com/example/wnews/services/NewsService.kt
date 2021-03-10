package com.example.wnews.services

import com.example.wnews.models.ListNews
import com.example.wnews.models.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsService {

    @GET("/comments")
    fun getNews(
        @Query("page") page: Int,
        @Header("access-token") accessToken: String,
        @Header("client") client: String,
        @Header("uid") uid: String
    ): Call<ListNews>

    @GET("/comments/{id}")
    fun getCommentDetail(
        @Path("id") id: Int,
        @Header("access-token") accessToken: String,
        @Header("client") client: String,
        @Header("uid") uid: String
    ): Call<ListNews>

}