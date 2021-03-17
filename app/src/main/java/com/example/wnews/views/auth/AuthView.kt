package com.example.wnews.views.auth

interface AuthView {

    fun onResponseSuccess()

    fun onResponseFailure(message: String)

    fun onRequestFailure()


}