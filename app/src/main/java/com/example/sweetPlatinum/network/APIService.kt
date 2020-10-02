package com.example.sweetPlatinum.network

import com.example.sweetPlatinum.pojo.*
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("v1/auth/login")
    fun validateLogin(@Body body: PostLoginBody): Observable<Response<LoginResponse>>

    @GET("v1/auth/me")
    fun autoLogin(@Header("Authorization") auth: String): Single<Response<AuthResponse>>

    @POST("v1/auth/register")
    fun registerUser(@Body bodyRegister: PostBodyRegister): Single<Response<RegisterResponse>>

    @PUT("v1/users")
    fun updateUser(
        @Header("Authorization") auth: String,
        @Body photo: RequestBody
    ): Single<Response<UpdateResponse>>

    @GET("v1/users")
    fun getProfileUser(@Header("Authorization") auth: String): Single<Response<GetProfileResponse>>

    @GET("v1/battle")
    fun getHistoryBattle(@Header("Authorization") auth: String): Single<Response<GetBattleResponse>>

    @POST("v1/battle")
    fun saveHistoryBattle(
        @Header("Authorization") auth: String,
        @Body bodyHistory: PostBattleBody
    ): Single<PostBattleResponse>
}