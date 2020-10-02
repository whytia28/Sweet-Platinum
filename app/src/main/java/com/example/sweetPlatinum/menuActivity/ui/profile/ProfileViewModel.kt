package com.example.sweetPlatinum.menuActivity.ui.profile

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetPlatinum.network.ApiService
import com.example.sweetPlatinum.pojo.GetProfileResponse
import com.example.sweetPlatinum.pojo.UpdateResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.ByteArrayOutputStream

class ProfileViewModel(private val apiService: ApiService) : ViewModel() {

    private val disposable = CompositeDisposable()
    val dataProfile = MutableLiveData<GetProfileResponse>()
    val dataProfileError = MutableLiveData<JSONObject>()
    val dataProfileUpdate = MutableLiveData<UpdateResponse>()

    fun getProfileUser(token: String) {

        disposable.add(
            apiService.getProfileUser(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.code() == 200) {
                        response.body()?.let {
                            dataProfile.postValue(it)
                        }
                    } else {
                        response.errorBody()?.string()?.let {
                            val jsonObject = JSONObject(it)
                            dataProfileError.postValue(jsonObject)
                        }
                    }

                }, {

                })
        )

    }

    fun updateUser(
        token: String,
        bitmap: Bitmap,
        username: String,
        email: String
    ) {
        val builder: MultipartBody.Builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        builder.addFormDataPart("username", username)
        builder.addFormDataPart("email", email)

        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 30, bos)

        builder.addFormDataPart(
            "photo",
            "photo.jpg",
            RequestBody.create(MultipartBody.FORM, bos.toByteArray())
        )

        val map = HashMap<String, String>()
        map["username"] = username
        map["email"] = email
        disposable.add(
            apiService.updateUser(token, builder.build())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.code() == 200) {
                        response.body()?.let {
                            dataProfileUpdate.postValue(it)
                        }
                    } else {
                        response.errorBody()?.string()?.let {
                            val jsonObject = JSONObject(it)
                            dataProfileError.postValue(jsonObject)
                        }
                    }

                }, {

                })
        )
    }

}