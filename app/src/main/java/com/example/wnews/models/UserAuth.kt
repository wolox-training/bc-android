package com.example.wnews.models

data class UserAuth(
    var token:String = "token",
    var client:String ="client",
    var uid:String = "uid"
)