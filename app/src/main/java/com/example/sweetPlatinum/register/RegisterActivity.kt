package com.example.sweetPlatinum.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.example.sweetPlatinum.login.LoginActivity

class RegisterActivity : AppCompatActivity(), RegisterActivityPresenter.Listener {

    /*private lateinit var binding : ActivityRegisterBinding
    private lateinit var presenter: RegisterActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        presenter = RegisterActivityPresenter(this)

        binding.iconArrowBack.setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }
        binding.textClickHere.setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }

        binding.btnRegister.setOnClickListener {
            if(binding.inputTextUsername.text.isNotEmpty() && binding.inputTextEmail.text.isNotEmpty() && binding.inputTextPassword.text.isNotEmpty()) {
                //here code to register
                presenter.registerPerson(
                    binding.inputTextUsername.text.toString(),
                    binding.inputTextEmail.text.toString(),
                    binding.inputTextPassword.text.toString()
                )
            }
            else if(binding.inputTextUsername.text.isEmpty() && binding.inputTextEmail.text.isEmpty() && binding.inputTextPassword.text.isEmpty()){
                Toast.makeText(this, "Username, Email dan Password anda Kosong", Toast.LENGTH_LONG).show()
                binding.inputTextUsername.requestFocus()
            }else if(binding.inputTextUsername.text.isEmpty() && binding.inputTextEmail.text.isNotEmpty() && binding.inputTextPassword.text.isNotEmpty()) {
                Toast.makeText(this, "Username anda Kosong", Toast.LENGTH_LONG).show()
                binding.inputTextUsername.requestFocus()
            }else if(binding.inputTextUsername.text.isNotEmpty() && binding.inputTextEmail.text.isEmpty() && binding.inputTextPassword.text.isNotEmpty()) {
                Toast.makeText(this, "Email anda Kosong", Toast.LENGTH_LONG).show()
                binding.inputTextEmail.requestFocus()
            }else if(binding.inputTextUsername.text.isNotEmpty() && binding.inputTextEmail.text.isNotEmpty() && binding.inputTextPassword.text.isEmpty()) {
                Toast.makeText(this, "Password anda Kosong", Toast.LENGTH_LONG).show()
                binding.inputTextPassword.requestFocus()
            }
            else {
                Toast.makeText(this, "Terdapat Kesalahan Pada Inputan", Toast.LENGTH_LONG).show()
            }
        }
    }*/

    override fun onRegisterSuccess(successMessage: String) {
        Toast.makeText(this, successMessage, Toast.LENGTH_LONG).show()
        startActivity(Intent(applicationContext, LoginActivity::class.java))
        finish()
    }

    override fun onRegisterFailure(failureMessage: String) {
        Toast.makeText(this, failureMessage, Toast.LENGTH_LONG).show()
        finish()
    }
}