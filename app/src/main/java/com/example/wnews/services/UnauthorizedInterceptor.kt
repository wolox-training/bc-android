package com.example.wnews.services

import android.content.Intent
import androidx.preference.PreferenceManager
import com.example.wnews.WNewsApplication
import com.example.wnews.views.auth.AuthPresenter
import com.example.wnews.views.auth.login.LoginActivity
import okhttp3.Interceptor
import okhttp3.Response


class UnauthorizedInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (response.code() == 401) {

            val context = WNewsApplication().getContext()

            val prefs = PreferenceManager.getDefaultSharedPreferences(context)

            if(AuthPresenter(prefs,null).isLoggedIn()) {

                AuthPresenter(prefs, null).killSession()
                val intent = Intent(context, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
                context.startActivity(intent)

            }
        }

        return response
    }
}