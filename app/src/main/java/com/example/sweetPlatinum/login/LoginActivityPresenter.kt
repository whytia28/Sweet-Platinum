package com.example.sweetPlatinum.login

import android.content.SharedPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivityPresenter(val listener : Listener) {

    /*fun loginPerson(email : String, password: String, sharedPreferences: SharedPreferences){

        val person =
            PostPersonLoginBody(
                email,
                password
            )

        ApiClient.apiService.loginPerson(person).enqueue(object :
            Callback<PostPersonLoginResponse> {
            override fun onFailure(call: Call<PostPersonLoginResponse>, t: Throwable) {
                listener.onLoginFailure(t.toString(), "Terjadi Kesalahan Pada Api Service")
            }

            override fun onResponse(
                call: Call<PostPersonLoginResponse>,
                response: Response<PostPersonLoginResponse>
            ) {
                if(response.isSuccessful && response.body()?.status == 200) {
//                    disini push data ke shared preference
                    val editor = sharedPreferences.edit()
                    editor.putString(FIELD_EMAIL, response.body()?.data?.email)
                    editor.putString(ID, response.body()?.data?.id.toString())
                    editor.putString(FIELD_USERNAME, response.body()?.data?.username)

                    val toastStatus = editor.commit()

                    if(toastStatus){
                        listener.onLoginSuccess("${response.body()?.message}", "Data Berhasil Disimpan")
                    }else{
                        listener.onLoginSuccess("${response.body()?.message}", "Data Gagal Disimpan")
                    }
                }else {
                    listener.onLoginFailure("${response.body()?.message}", "Data Tidak Ada")
                }
            }
        })
    }*/

    interface Listener{
        fun onLoginSuccess(successMessage: String, successSaveData: String)
        fun onLoginFailure(failureMessage: String, failedSaveData: String)
    }

}