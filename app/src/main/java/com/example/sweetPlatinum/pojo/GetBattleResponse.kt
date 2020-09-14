package com.example.binarchapter8.pojo


import com.google.gson.annotations.SerializedName

data class GetBattleResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("success")
    val success: Boolean
) {
    data class Data(
        @SerializedName("createdAt")
        val createdAt: String,
        @SerializedName("_id")
        val id: String,
        @SerializedName("message")
        val message: String,
        @SerializedName("mode")
        val mode: String,
        @SerializedName("result")
        val result: String,
        @SerializedName("updatedAt")
        val updatedAt: String
    )
}