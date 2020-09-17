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
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class LoginActivity : AppCompatActivity(), LoginActivityPresenter.Listener {

    private val presenter: LoginActivityPresenter by inject { parametersOf(this) }
    private var rememberMe: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.listener = this

        btn_login.setOnClickListener {
            presenter.loginPerson(et_email.text.toString(), etPassword.text.toString(), rememberMe)

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

    override fun onLoginSuccess() {
        Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_LONG).show()
    }

    override fun onLoginFailure(failureMessage: String) {
        Toast.makeText(this, failureMessage, Toast.LENGTH_LONG).show()
        finish()
    }

    override fun goToRegister() {
        val registerIntent = Intent(this, RegisterActivity::class.java)
        startActivity(registerIntent)
    }

    override fun goToMenuActivity(data: LoginResponse.Data) {
        val menuIntent = Intent(this, MenuActivity::class.java)
        menuIntent.putExtra("data", data)
        startActivity(menuIntent)
        finish()
    }

    override fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }

    override fun saveToken(key: String, data: String) {
        MySharedPreferences(applicationContext).putData(key, data)
    }

    override fun resetEditText() {
        et_email.setText("")
        etPassword.setText("")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }
}