package com.example.wnews.services

import com.example.wnews.models.Comment
import com.example.wnews.models.UserAuth
import retrofit2.Call
import retrofit2.http.*


interface NewsService {

    @POST("/auth/sign_in")
    fun createUser(@Header("email") email:String,@Header("password") password:String): Call<UserAuth?>?

    @GET("/comments")
    fun getComments(@Header("access-token") accessToken:String,@Header("client") client:String,@Header("uid") uid:String):Call<List<Comment>>

    @GET("/comments/{id}")
    fun getCommentDetail(@Path("id") id: Int, @Header("access-token") accessToken:String, @Header("client") client:String, @Header("uid") uid:String):Call<List<Comment>>

}