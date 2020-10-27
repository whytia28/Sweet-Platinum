package com.example.sweetPlatinum.pojo


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class LoginResponse() {

    var t: Throwable? = null
    @SerializedName("data")
    var `data`: Data? = null
    @SerializedName("success")
    var success: Boolean? = false

    constructor(t: Throwable) : this() {
        this.t = t
    }

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