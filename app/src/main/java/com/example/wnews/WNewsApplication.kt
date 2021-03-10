package com.example.wnews

import android.app.Application
import android.content.Context
import com.example.wnews.providers.RetrofitProvider
import com.facebook.drawee.backends.pipeline.Fresco


class WNewsApplication: Application() {

    private var context:Context? = null

    override fun onCreate() {
        super.onCreate()

        context = applicationContext
        instanceRetrofit()
        instanceFresco()
    }

    private fun instanceRetrofit(){
        RetrofitProvider.authService
        RetrofitProvider.newsService
    }

    private fun instanceFresco(){
        Fresco.initialize(this)
    }

    fun getContext():Context{
        return context!!
    }

}