package com.example.wnews.views.auth

import android.content.SharedPreferences
import com.example.wnews.models.User
import com.example.wnews.models.UserAuth
import com.example.wnews.providers.RetrofitProvider
import com.google.gson.Gson
import okhttp3.Headers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AuthPresenter(var sharedPref: SharedPreferences,val view: AuthView?){



    fun onResponseLogIn(user: User){

        val call: Call<UserAuth?>? = RetrofitProvider.authService.createUser(user.email,user.password)
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

                    view!!.onAuthResponse(response.code())

                } else if (response.errorBody() != null) {

                    view!!.onAuthResponse(response.code())

                }

            }

            override fun onFailure(call: Call<UserAuth?>?, t: Throwable?) {
                view!!.onAuthResponse(0)
            }


        })
    }

    fun isLoggedIn():Boolean{
        return !sharedPref.getString("UserAuth", "").isNullOrEmpty()

    }

    fun getUserAuth():UserAuth{
        val sharedPrefString = sharedPref.getString("UserAuth", "")
        return Gson().fromJson(sharedPrefString,UserAuth::class.java)

    }

    private fun saveUserAuth(newUserAuth: UserAuth){

        val editor = sharedPref.edit()
        editor.putString("UserAuth", Gson().toJson(newUserAuth).toString())
        editor.apply()

    }

    fun killSession(){

        val editor = sharedPref.edit()
        editor.putString("UserAuth", "")
        editor.apply()

    }


}