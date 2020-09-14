package com.example.sweetPlatinum.pojo


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class LoginResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("success")
    val success: Boolean
) {
    @Parcelize
    data class Data(
        @SerializedName("email")
        var email: String,
        @SerializedName("_id")
        val id: String,
        @SerializedName("token")
        val token: String,
        @SerializedName("username")
        var username: String
    ) : Parcelable
}