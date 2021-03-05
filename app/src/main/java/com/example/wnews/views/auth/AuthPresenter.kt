package com.example.wnews.providers

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.wnews.R
import com.example.wnews.models.User
import com.example.wnews.models.UserAuth
import com.example.wnews.services.NewsService
import com.example.wnews.services.Retrofit
import okhttp3.Headers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LogInSignUpProvider(var context: Context){

    private val sharedPref: SharedPreferences = context.getSharedPreferences(
        context.getString(R.string.sharedPreferenceUserAuth),
        0
    )

    fun logIn(user: User){

        val service: NewsService = Retrofit().logIn()!!.create(
            NewsService::class.java
        )

        val call: Call<UserAuth?>? = service.createUser(user.email,user.password)
        call!!.enqueue(object : Callback<UserAuth?> {

            override fun onResponse(
                call: Call<UserAuth?>?,
                response: Response<UserAuth?>?
            ) {

                if (response!!.isSuccessful) {

                    val headerList: Headers = response.headers()

                    val userAuth = UserAuth()
                    userAuth.apply {
                        token = headerList["access-token"].toString()
                        client = headerList["client"].toString()
                        uid = user.email
                    }

                    saveUserAuth(userAuth)

                } else if (response.errorBody() != null) {

                    Log.d("userAuth", response.errorBody().string())

                }

            }

            override fun onFailure(call: Call<UserAuth?>?, t: Throwable?) {
            }


        })
    }

    fun isLoggedIn():Boolean{

        return !sharedPref.getString(context.getString(R.string.sharedPreferenceUserAuth), "").isNullOrBlank()

    }

    fun getUserAuth():String?{

        return sharedPref.getString(context.getString(R.string.sharedPreferenceUserAuth), "")

    }

    private fun saveUserAuth(newToken: UserAuth){

        val editor = sharedPref.edit()
        editor.putString(context.getString(R.string.sharedPreferenceUserAuth), newToken.toString())
        editor.apply()

    }

    fun killSession(){

        val editor = sharedPref.edit()
        editor.putString(context.getString(R.string.sharedPreferenceUserAuth), "")
        editor.apply()

    }


}