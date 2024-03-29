package com.example.sweetPlatinum.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sweetPlatinum.R
import com.example.sweetPlatinum.menuActivity.MenuActivity
import com.example.sweetPlatinum.pojo.LoginResponse
import com.example.sweetPlatinum.register.RegisterActivity
import com.example.sweetPlatinum.sharedPreference.MySharedPreferences
import com.example.sweetPlatinum.utils.AnimUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()
    private var rememberMe: Boolean = false
    private lateinit var animUtil: AnimUtil


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.title = getString(R.string.title_login)

        animUtil = AnimUtil()

        btn_login.setOnClickListener {
            showProgressBar()
            viewModel.loginPerson(
                et_email.text.toString(),
                etPassword.text.toString(),
                rememberMe
            )
        }
        check_box.setOnCheckedChangeListener { _, isChecked ->
            rememberMe = isChecked
        }
        btn_reset.setOnClickListener {
            resetEditText()
        }
        tv_here.setOnClickListener {
            animUtil.bounceAnimation(it)
            goToRegister()
        }

        observeLogin()
        observeError()

    }

    private fun observeLogin() {
        viewModel.loginData.observe(this, {
            if (it.t == null) {
                saveToken("token", "Bearer ${it.data?.token}")
                onLoginSuccess()
                it.data?.let { it1 -> goToMenuActivity(it1) }
            } else {
                it.t?.message?.let { it1 -> onLoginFailure(it1) }
            }
            hideProgressBar()
        })
    }

    private fun observeError() {
        viewModel.errorData.observe(this, {
            Toast.makeText(this, it.getString("errors"), Toast.LENGTH_SHORT)
                .show()
            hideProgressBar()
        })
    }

    private fun onLoginSuccess() {
        Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_LONG).show()
    }

    private fun onLoginFailure(failureMessage: String) {
        Toast.makeText(this, failureMessage, Toast.LENGTH_LONG).show()
    }

    private fun goToRegister() {
        val registerIntent = Intent(this, RegisterActivity::class.java)
        startActivity(registerIntent)
        overridePendingTransition(R.anim.from_right, R.anim.to_left)
    }

    private fun goToMenuActivity(data: LoginResponse.Data) {
        val menuIntent = Intent(this, MenuActivity::class.java)
        menuIntent.putExtra("data", data)
        startActivity(menuIntent)
        overridePendingTransition(R.anim.from_right, R.anim.to_left)
        finish()
    }

    private fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }

    private fun saveToken(token: String, data: String) {
        MySharedPreferences(applicationContext).putData(token, data)
    }

    private fun resetEditText() {
        et_email.setText("")
        etPassword.setText("")
    }

}