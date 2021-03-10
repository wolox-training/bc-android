package com.example.wnews.models

import com.google.gson.annotations.SerializedName

data class ListNews (
        @SerializedName("page")
        var page : List<News>
)