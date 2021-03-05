package com.example.wnews.services

import com.example.wnews.Config
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Retrofit {

    private var retrofit: Retrofit? = null

    fun requestService(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Config().BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}