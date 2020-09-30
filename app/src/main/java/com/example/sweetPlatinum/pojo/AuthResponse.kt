package com.example.sweetPlatinum.pojo


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class AuthResponse() {
    //No 1
    //Di AuthResponse ada property Throwable
    //Lanjut ke SweetRepository Method autoLogin
    var t: Throwable? = null

    @SerializedName("data")
    var `data`: Data? = null
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