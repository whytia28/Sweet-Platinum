package com.example.sweetPlatinum.pojo


import com.google.gson.annotations.SerializedName

class RegisterResponse() {
    var t: Throwable? = null
    @SerializedName("data")
    lateinit var `data`: Data
    @SerializedName("success")
    lateinit var success: String

    constructor(t: Throwable): this(){
        this.t = t
    }

    data class Data(
        @SerializedName("email")
        val email: String,
        @SerializedName("_id")
        val id: String,
        @SerializedName("username")
        val username: String
    )
}