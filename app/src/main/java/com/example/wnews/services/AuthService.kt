package com.example.wnews.services

import com.example.wnews.models.UserAuthResponse
import retrofit2.Call
import retrofit2.http.*


interface AuthService {

    @POST("/auth/sign_in")
    fun createUser( @Header("email") email:String , @Header("password") password:String): Call<UserAuthResponse?>?

}