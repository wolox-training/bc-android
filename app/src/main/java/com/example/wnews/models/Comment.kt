package com.example.wnews.models

data class Comment(
    var id : Int = 0,
    var commenter : String = "",
    var comment : String = "",
    var date : String = "",
    var avatar : String = ""
)