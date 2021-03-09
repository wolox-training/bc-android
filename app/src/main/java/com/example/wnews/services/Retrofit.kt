package com.example.wnews.services

import com.example.wnews.Config
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Retrofit {

    private var retrofit: Retrofit? = null

    private var client = OkHttpClient.Builder()
            .addInterceptor(UnauthorizedInterceptor())
            .build()

    fun requestService(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Config().BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}