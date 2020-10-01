package com.example.sweetPlatinum.repository

import android.content.Context
import android.graphics.Bitmap
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sweetPlatinum.network.ApiService
import com.example.sweetPlatinum.pojo.*
import com.example.sweetPlatinum.room.History
import com.example.sweetPlatinum.room.HistoryDAO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Response
import java.io.ByteArrayOutputStream


class SweetRepository(private val historyDAO: HistoryDAO, private val apiService: ApiService) {

    private val disposable = CompositeDisposable()
    private val listHistoryLocal = MutableLiveData<List<History>>()
    private val listHistory = MutableLiveData<List<GetBattleResponse.Data>>()
    private val history = MutableLiveData<PostBattleResponse>()
    private val authData = MutableLiveData<AuthResponse>()
    private val loginData = MutableLiveData<LoginResponse>()
    private val registerData = MutableLiveData<Response<RegisterResponse>>()


    fun autoLogin(token: String, context: Context): LiveData<AuthResponse> {
        disposable.add(
            apiService.autoLogin(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    authData.postValue(it)
                }, {
                    authData.postValue(AuthResponse(it))
                })
        )
        return authData
    }

    fun loginPerson(
        context: Context,
        email: String,
        password: String,
        rememberMe: Boolean
    ): LiveData<LoginResponse> {
        val body = PostLoginBody(email, password)
        disposable.add(
            apiService.validateLogin(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.code() == 200) {
                        response.body()?.let {
                            if (rememberMe) {
                                loginData.postValue(it)
                            }
                        }
                    } else {
                        response.errorBody()?.string()?.let {
                            val jsonObject = JSONObject(it)
                            Toast.makeText(
                                context,
                                jsonObject.getString("errors"),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }, {
                    loginData.postValue(LoginResponse(it))
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                })
        )
        return loginData
    }

    fun registerPerson(
        context: Context,
        email: String,
        username: String,
        password: String
    ): LiveData<Response<RegisterResponse>> {
        val body = PostBodyRegister(email, password, username)
        disposable.add(
            apiService.registerUser(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    registerData.postValue(response)
                }, {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                })
        )
        return registerData
    }

    fun getHistory(token: String) {
        disposable.add(
            apiService.getHistoryBattle(token).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    response.body()?.data.let {
                        if (it != null) {
                            listHistory.postValue(it)
                        }
                    }

                }, {

                })
        )
    }

    fun getProfileUser(token: String) {
        disposable.add(
            apiService.getProfileUser(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                }, {

                })
        )
    }

    fun updateUser(token: String, bitmap: Bitmap, username: String, email: String) {
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
                .subscribe({

                }, {

                })
        )
    }

    fun saveHistory(token: String, body: PostBattleBody): LiveData<PostBattleResponse> {
        disposable.add(
            apiService.saveHistoryBattle(token, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    history.postValue(it)
                }, {

                })
        )
        return history
    }

    fun saveHistoryLocal(history: History) {
        GlobalScope.launch {
            historyDAO.create(history)
        }
    }

    fun getListHistory(): List<History> {
        return historyDAO.read()
    }

    fun deleteAllHistory() {
        GlobalScope.launch {
            historyDAO.deleteAll()
        }
    }

    fun dispose() {
        disposable.dispose()
    }
}