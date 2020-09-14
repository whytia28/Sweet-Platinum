package com.example.sweetPlatinum.register

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivityPresenter(val listener : Listener) {

    fun registerPerson(username : String, email : String, password: String){
        val person =
            PostPersonRegisterBody(
                username,
                email,
                password
            )

        ApiClient.apiService.registerPerson(person).enqueue(object :
            Callback<PostPersonRegisterResponse> {
            override fun onFailure(call: Call<PostPersonRegisterResponse>, t: Throwable) {
                listener.onRegisterFailure(t.toString())
            }
            override fun onResponse(
                call: Call<PostPersonRegisterResponse>,
                response: Response<PostPersonRegisterResponse>
            ) {
                if(response.isSuccessful && response.body()?.status == 201) {
                    listener.onRegisterSuccess("${response.body()?.message}")
                }else {
                    listener.onRegisterFailure("${response.body()?.message}")
                }
            }
        })
    }

    interface Listener{
        fun onRegisterSuccess(successMessage: String)
        fun onRegisterFailure(failureMessage: String)
    }
}