package com.example.wnews.models

import com.google.gson.annotations.SerializedName

data class LikeResponse(

    @SerializedName("message")
    var message: String
)