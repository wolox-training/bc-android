package com.example.wnews

import android.app.Application
import com.example.wnews.services.RetrofitProvider
import com.facebook.drawee.backends.pipeline.Fresco

class StartApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        instanceRetrofit()
        instanceFresco()
    }

    private fun instanceRetrofit(){
        RetrofitProvider.getAuthService()
    }

    private fun instanceFresco(){
        Fresco.initialize(this);
    }
}