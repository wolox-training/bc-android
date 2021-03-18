package com.example.wnews

import android.app.Application
import android.content.Context
import androidx.preference.PreferenceManager
import com.example.wnews.providers.RetrofitProvider
import com.facebook.drawee.backends.pipeline.Fresco


class WNewsApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        initializeUserPreference()
        initializeRetrofit()
        initializeFresco()
    }

    private fun initializeRetrofit() {

        RetrofitProvider.authService
        RetrofitProvider.newsService

    }

    private fun initializeFresco() {
        Fresco.initialize(this)
    }

    private fun initializeUserPreference() {

        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(this)
        UserProvider.intanceUserAuth(sharedPreference)

    }

    init {
        instance = this
    }

    companion object {
        private var instance: WNewsApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

}