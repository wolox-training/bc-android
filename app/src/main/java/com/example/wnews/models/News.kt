package com.example.wnews.models


import com.google.gson.annotations.SerializedName
import org.json.JSONArray

data class News(

        @SerializedName("id")
        var newsId : Int = 0,

        @SerializedName("commenter")
        var title : String = "",

        @SerializedName("comment")
        var detail : String = "",

        @SerializedName("avatar")
        var imageUrl : String = "",

        @SerializedName("likes")
        var like: List<Int> = listOf<Int>()
)