package com.example.wnews.providers

import com.example.wnews.services.AuthService
import com.example.wnews.services.NewsService
import com.example.wnews.services.Retrofit

object RetrofitProvider {

    var authService: AuthService = Retrofit().requestService()!!.create(AuthService::class.java)
        private set


    var newsService: NewsService = Retrofit().requestService()!!.create(NewsService::class.java)
        private set

}