package com.example.wnews.models

import com.google.gson.annotations.SerializedName

data class UserAuthResponse(

    @SerializedName("data")
    var data: UserAuth

)