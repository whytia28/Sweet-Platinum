package com.example.binarchapter8.pojo


import com.google.gson.annotations.SerializedName

data class PostBodyRegister(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)