package com.example.wnews.providers

import android.content.ContentProvider
import com.example.wnews.models.User

interface LogInSignUpProvider {

    fun logIn(user:User){

    }

    fun isLoggedIn():Boolean{
        return false
    }
}