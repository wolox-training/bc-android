package com.example.wnews

import android.app.Application
import android.content.Context
import androidx.preference.PreferenceManager
import com.example.wnews.providers.RetrofitProvider
import com.facebook.drawee.backends.pipeline.Fresco


class WNewsApplication : Application() {

    private var context: Context? = null

    override fun onCreate() {
        super.onCreate()

        context = applicationContext
        intanceUserPreference()
        instanceRetrofit()
        instanceFresco()
    }

    private fun instanceRetrofit() {

        RetrofitProvider.authService
        RetrofitProvider.newsService
    }

    private fun instanceFresco() {
        Fresco.initialize(this)
    }

    fun getContext(): Context {
        return context!!

    }

    private fun intanceUserPreference() {
        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(this)
        UserProvider.intanceUserAuth(sharedPreference)

    }

}