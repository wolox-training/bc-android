package com.example.wnews.models

import com.google.gson.annotations.SerializedName

data class UserAuth(

        @SerializedName("token")
        var token:String = "token",

        @SerializedName("client")
        var client:String ="client",

        @SerializedName("uid")
        var uid:String = "uid"
)