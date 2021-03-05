package com.example.wnews.models

data class LoginResponse(
    var token:String = "",
    var client:String ="",
    var uid:String = ""
)