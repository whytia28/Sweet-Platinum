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
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {

        private val viewModel: LoginViewModel by inject()
    private var rememberMe: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.title = getString(R.string.title_login)

        btn_login.setOnClickListener {
            showProgressBar()
            viewModel.loginPerson(
                this,
                et_email.text.toString(),
                etPassword.text.toString(),
                rememberMe
            ).observe(this, {
                if (it.t == null) {
                    onLoginSuccess()
                    it.data?.let {
                            it1 -> goToMenuActivity(it1) }
                } else {
                    it.t?.message?.let { it1 -> onLoginFailure(it1) }
                }
                hideProgressBar()
            })

        }
        check_box.setOnCheckedChangeListener { _, isChecked ->
            rememberMe = isChecked
        }
        btn_reset.setOnClickListener {
            resetEditText()
        }
        tv_here.setOnClickListener {
            goToRegister()
        }

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
    }

    private fun goToMenuActivity(data: LoginResponse.Data) {
        val menuIntent = Intent(this, MenuActivity::class.java)
        menuIntent.putExtra("data", data)
        startActivity(menuIntent)
        finish()
    }

    private fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }

//    fun saveToken(key: String, data: String) {
//        MySharedPreferences(applicationContext).putData(key, data)
//    }

    private fun resetEditText() {
        et_email.setText("")
        etPassword.setText("")
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        presenter.dispose()
//    }
}