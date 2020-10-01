package com.example.sweetPlatinum.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sweetPlatinum.R
import com.example.sweetPlatinum.login.LoginActivity
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject
import org.koin.android.ext.android.inject

class RegisterActivity : AppCompatActivity() {

    private val viewModel : RegisterViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.title = getString(R.string.title_register)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btn_register.setOnClickListener {
            showProgressBar()
            viewModel.registerPerson(
                this,
                et_email.text.toString(),
                et_username.text.toString(),
                etPassword.text.toString()
            ).observe(this, {response ->
                if (response.code() == 422) {
                    response.errorBody()?.string()?.let {
                        val jsonObject = JSONObject(it)
                        Toast.makeText(this, jsonObject.getString("errors"), Toast.LENGTH_SHORT).show()
                    }
                } else {
                    onRegisterSuccess()
                }
                hideProgressBar()
            })
        }
        btn_reset.setOnClickListener {
            resetEditText()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun onRegisterSuccess() {
        Toast.makeText(this, getString(R.string.register_success), Toast.LENGTH_LONG).show()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun resetEditText() {
        et_username.setText("")
        etPassword.setText("")
        et_email.setText("")
    }

    private fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }

}