package com.example.wnews.models

import com.google.gson.annotations.SerializedName

data class UserAuth(

    @SerializedName("id")
    var userAuthId: Int = 0,

    @SerializedName("token")
    var token: String = "token",

    @SerializedName("client")
    var client: String = "client",

    @SerializedName("uid")
    var uid: String = "uid"

)