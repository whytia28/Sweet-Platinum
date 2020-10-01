package com.example.sweetPlatinum.register

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.sweetPlatinum.repository.SweetRepository

class RegisterViewModel(private val repository: SweetRepository) : ViewModel() {
    fun registerPerson(context: Context, email: String, username: String, password: String) =
        repository.registerPerson(context, email, username, password)

}