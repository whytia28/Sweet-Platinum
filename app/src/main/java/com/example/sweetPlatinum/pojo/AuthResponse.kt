package com.example.sweetPlatinum.pojo


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
class AuthResponse() {

    var t: Throwable? = null
    @SerializedName("data")
    val `data`: Data? = null
    @SerializedName("success")
    var success: Boolean = false

    constructor(t: Throwable) : this() {
        this.t = t
    }
    @Parcelize
    data class Data(
        @SerializedName("email")
        val email: String?,
        @SerializedName("_id")
        val id: String?,
        @SerializedName("username")
        val username: String?
    ) : Parcelable
}