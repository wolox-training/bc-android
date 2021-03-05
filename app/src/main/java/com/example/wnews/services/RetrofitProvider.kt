package com.example.wnews.services

object RetrofitProvider {

    private var authService:AuthService = Retrofit().requestService()!!.create(
        AuthService::class.java
    )

    fun getAuthService():AuthService = authService

}