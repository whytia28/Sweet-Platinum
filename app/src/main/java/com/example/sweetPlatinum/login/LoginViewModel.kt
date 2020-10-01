package com.example.sweetPlatinum.login

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.sweetPlatinum.repository.SweetRepository


class LoginViewModel(private val repository: SweetRepository) : ViewModel() {
    fun loginPerson(context: Context, email: String, password: String, rememberMe: Boolean) =
        repository.loginPerson(context, email, password, rememberMe)
}