package com.example.sweetPlatinum.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.example.sweetPlatinum.R
import com.example.sweetPlatinum.login.LoginActivity
import kotlinx.android.synthetic.main.activity_register.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class RegisterActivity : AppCompatActivity(), RegisterActivityPresenter.Listener {

    private val presenter: RegisterActivityPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        presenter.listener = this

        btn_register.setOnClickListener {
            presenter.registerPerson(et_email.text.toString(), et_username.text.toString(), etPassword.text.toString())
        }
        btn_reset.setOnClickListener {
            resetEditText()
        }
        btn_back.setOnClickListener {
            onBackPressed()
        }
    }
    override fun onRegisterSuccess() {
        Toast.makeText(this, getString(R.string.register_success), Toast.LENGTH_LONG).show()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onRegisterFailure(failureMessage: String) {
        Toast.makeText(this, failureMessage, Toast.LENGTH_LONG).show()
    }

    override fun resetEditText() {
        et_username.setText("")
        etPassword.setText("")
        et_email.setText("")
    }

    override fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }

}