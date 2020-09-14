package com.example.sweetPlatinum.pojo


import com.google.gson.annotations.SerializedName

data class PostBattleBody(
    @SerializedName("mode")
    val mode: String,
    @SerializedName("result")
    val result: String
)