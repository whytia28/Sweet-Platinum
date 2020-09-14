package com.example.binarchapter8.pojo


import com.google.gson.annotations.SerializedName

data class PostBattleBody(
    @SerializedName("mode")
    val mode: String,
    @SerializedName("result")
    val result: String
)