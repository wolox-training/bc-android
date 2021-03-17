package com.example.wnews

import android.content.SharedPreferences
import com.example.wnews.models.UserAuth
import com.google.gson.Gson

object UserProvider {

    private const val USER_AUTH = "USER_AUTH"

    lateinit var userAuth: UserAuth
        private set


    fun isLoggedIn(): Boolean {
        return userAuth.userAuthId != 0

    }

    fun intanceUserAuth(sharedPreferences: SharedPreferences) {

        val sharedPrefString = sharedPreferences.getString(USER_AUTH, "")
        userAuth = if (sharedPrefString!!.isEmpty()) {
            UserAuth()
        } else {
            Gson().fromJson(sharedPrefString, UserAuth::class.java)
        }

    }

    fun saveUserAuth(newUserAuth: UserAuth, sharedPreferences: SharedPreferences) {

        val editor = sharedPreferences.edit()
        editor.putString(USER_AUTH, Gson().toJson(newUserAuth).toString())
        editor.apply()

        userAuth = newUserAuth

    }

    fun killSession(sharedPreferences: SharedPreferences) {

        val editor = sharedPreferences.edit()
        editor.putString(USER_AUTH, UserAuth().toString())
        editor.apply()

        userAuth = UserAuth()

    }
}