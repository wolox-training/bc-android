package com.example.wnews.views.auth

import android.content.SharedPreferences
import com.example.wnews.UserProvider
import com.example.wnews.models.User
import com.example.wnews.models.UserAuth
import com.example.wnews.models.UserAuthResponse
import com.example.wnews.providers.RetrofitProvider
import com.example.wnews.utils.FormatUtils
import okhttp3.Headers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AuthPresenter(var sharedPref: SharedPreferences, val view: AuthView?) {

    fun validateUserMail(userMail: String): Boolean {

        return FormatUtils().EMAIL_ADDRESS_PATTERN.matcher(userMail).matches()
    }

    fun isUserMailEmpty(userMail: String) = userMail.isEmpty()


    fun isUserPasswordEmpty(password: String) = password.isEmpty()

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

                    UserProvider.saveUserAuth(userAuth, sharedPref)

                    view!!.onResponseSuccess()

                } else if (response.errorBody() != null) {

                    view!!.onResponseFailure(response.message())

                }

            }

            override fun onFailure(call: Call<UserAuthResponse?>?, t: Throwable?) {
                view!!.onRequestFailure()
            }


        })
    }


}