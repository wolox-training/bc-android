package com.example.wnews.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Retrofit {

    private var retrofit: Retrofit? = null
    private val BASE_URL = "https://w-android-training.herokuapp.com"

    fun requestService(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}