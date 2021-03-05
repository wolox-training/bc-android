package com.example.wnews.views.auth

import android.content.SharedPreferences
import android.util.Log
import com.example.wnews.models.User
import com.example.wnews.models.UserAuth
import com.example.wnews.services.RetrofitProvider
import okhttp3.Headers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AuthPresenter(var sharedPref: SharedPreferences,val view: AuthView?){



    fun onResponseLogIn(user: User){

        val call: Call<UserAuth?>? = RetrofitProvider.getAuthService().createUser(user.email,user.password)
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

                    view!!.onAuthResponse(true)

                } else if (response.errorBody() != null) {

                    view!!.onAuthResponse(false)

                }

            }

            override fun onFailure(call: Call<UserAuth?>?, t: Throwable?) {
            }


        })
    }

    fun isLoggedIn():Boolean{
        Log.d("userAuth", sharedPref.getString("UserAuth", "")!!)
        return !sharedPref.getString("UserAuth", "").isNullOrEmpty()

    }

    fun getUserAuth():String?{

        return sharedPref.getString("UserAuth", "")

    }

    private fun saveUserAuth(newUserAuth: UserAuth){

        val editor = sharedPref.edit()
        editor.putString("UserAuth", newUserAuth.toString())
        editor.apply()

    }

    fun killSession(){

        val editor = sharedPref.edit()
        editor.putString("UserAuth", "")
        editor.apply()

    }


}