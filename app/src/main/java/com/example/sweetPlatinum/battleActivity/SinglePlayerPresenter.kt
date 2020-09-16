package com.example.sweetPlatinum.battleActivity

import com.example.binarchapter8.pojo.PostBattleBody
import com.example.binarchapter8.pojo.PostBattleResponse
import com.example.sweetPlatinum.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SinglePlayerPresenter(private val apiService: ApiService) {

    var listener: Listener? = null

    fun showResult() {
        listener?.showResult()
    }

    fun startNew() {
        listener?.startNew()
    }

    fun setOverlay() {
        listener?.setOverlay()
    }

    fun setCpuOverlay() {
        listener?.setCpuOverlay()
    }

    fun saveHistory(token: String, body: PostBattleBody) {
        apiService.saveHistoryBattle(token, body).enqueue(object : Callback<PostBattleResponse> {
            override fun onResponse(
                call: Call<PostBattleResponse>,
                response: Response<PostBattleResponse>
            ) {
                listener?.showSuccessSave()
            }

            override fun onFailure(call: Call<PostBattleResponse>, t: Throwable) {
                t.message?.let {
                    listener?.showFailedSave(it)
                }
            }

        })
    }


    interface Listener {
        fun startNew()
        fun setOverlay()
        fun showResult()
        fun setCpuOverlay()
        fun showSuccessSave()
        fun showFailedSave(errorMessage: String)
    }
}