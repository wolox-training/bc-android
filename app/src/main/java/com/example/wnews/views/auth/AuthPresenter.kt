package com.example.wnews.views.auth

import android.content.SharedPreferences
import android.util.Log
import com.example.wnews.UserProvider
import com.example.wnews.models.UserAuthResponse
import com.example.wnews.models.User
import com.example.wnews.models.UserAuth
import com.example.wnews.providers.RetrofitProvider
import com.google.gson.Gson
import okhttp3.Headers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AuthPresenter(var sharedPref: SharedPreferences, val view: AuthView?) {


    fun onResponseLogIn(user: User) {

        val call: Call<UserAuthResponse?>? =
            RetrofitProvider.authService.createUser(user.email, user.password)
        call!!.enqueue(object : Callback<UserAuthResponse?> {

            override fun onResponse(
                call: Call<UserAuthResponse?>?,
                response: Response<UserAuthResponse?>?
            ) {

                if (response!!.isSuccessful) {

                    val id = response.body()!!.data.userAuthId
                    val headerList: Headers = response.headers()
                    val userAuth = UserAuth().apply {
                        userAuthId = id
                        token = headerList["access-token"].toString()
                        client = headerList["client"].toString()
                        uid = user.email
                    }

                    UserProvider.saveUserAuth(userAuth,sharedPref)

                    view!!.onAuthResponse(response.code())

                } else if (response.errorBody() != null) {

                    view!!.onAuthResponse(response.code())

                }

            }

            override fun onFailure(call: Call<UserAuthResponse?>?, t: Throwable?) {
                Log.d("ResponseError", t!!.message.toString())
                view!!.onAuthResponse(0)
            }


        })
    }




}