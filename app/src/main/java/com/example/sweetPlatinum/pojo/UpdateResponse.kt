package com.example.binarchapter8.pojo


import com.google.gson.annotations.SerializedName

data class UpdateResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("success")
    val success: Boolean
) {
    data class Data(
        @SerializedName("email")
        val email: String,
        @SerializedName("_id")
        val id: String,
        @SerializedName("photo")
        val photo: String,
        @SerializedName("username")
        val username: String
    )
}