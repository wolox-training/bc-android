package com.example.wnews.services

import com.example.wnews.models.LikeResponse
import com.example.wnews.models.ListNewsResponse
import retrofit2.Call
import retrofit2.http.*

interface NewsService {

    @GET("/comments")
    fun getNews(
        @Query("page") page: Int,
        @Header("access-token") accessToken: String,
        @Header("client") client: String,
        @Header("uid") uid: String
    ): Call<ListNewsResponse>

    @PUT("comments/like/{id}")
    fun putLike(
        @Path("id") id: Int,
        @Header("access-token") accessToken: String,
        @Header("client") client: String,
        @Header("uid") uid: String
    ): Call<LikeResponse>


}