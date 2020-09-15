package com.example.sweetPlatinum.menuActivity.ui.profile

import android.graphics.Bitmap
import com.example.sweetPlatinum.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream

class ProfilePresenter(private val apiService: ApiService) {

    var listener: Listener? = null

    fun getProfileUser(token: String) {
        listener?.showProgressBar()
        apiService.getProfileUser(token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listener?.showProfile(it.data.username, it.data.email, it.data.photo)
                listener?.hiddenProgressBar()
            }, {
                listener?.showProfileFailed(it.localizedMessage)
            })
    }

    fun updateUser(token: String, bitmap: Bitmap, username: String, email: String) {
        listener?.showProgressBar()
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
        apiService.updateUser(token, builder.build())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listener?.hiddenProgressBar()
                listener?.onUpdateSuccess()
                listener?.showProfile(it.data.username, it.data.email, it.data.photo)
            }, {
                listener?.hiddenProgressBar()
                it.message?.let { it1 -> listener?.onUpdateFailed(it1) }
            })
    }

    interface Listener {
        fun showEditUi()
        fun showSetupUi()
        fun showProgressBar()
        fun hiddenProgressBar()
        fun onUpdateSuccess()
        fun showProfile(username: String, email: String, photo: String)
        fun showProfileFailed(errorMessage: String)
        fun onUpdateFailed(errorMessage: String)
    }
}