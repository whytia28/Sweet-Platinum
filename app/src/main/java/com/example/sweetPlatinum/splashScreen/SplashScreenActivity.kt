package com.example.sweetPlatinum.splashScreen

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sweetPlatinum.R
import com.example.sweetPlatinum.login.LoginActivity
import com.example.sweetPlatinum.menuActivity.MenuActivity
import com.example.sweetPlatinum.pojo.AuthResponse
import com.example.sweetPlatinum.register.RegisterActivity
import com.example.sweetPlatinum.sharedPreference.MySharedPreferences
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SplashScreenActivity : AppCompatActivity() {

    private val splashTimeOut: Long = 6000 // 2
    private lateinit var sharedPref: SharedPreferences


    private val viewModel: SplashScreenViewModel by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        sharedPref = applicationContext.getSharedPreferences("userData", Context.MODE_PRIVATE)


        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            if (sharedPref.contains("token")) {
                autoLogin()
            } else {
                goToLandingPage()
            }

            // close this activity
        }, splashTimeOut)
    }

    private fun autoLogin() {
        val token = MySharedPreferences(this).getData("token").toString()
        viewModel.autoLogin(token, this).observe(this, {
            //Diseleksi, kalau Throwablenya null, berarti masuk onResponse, kalo throwablenya gak nul, berarti masuk onFailure

            if(it.t != null){
                //onResponse
                it?.data?.let { response ->
                    goToMenuActivity(response)
                }
            }else{
                //onFailure bisa memanggil throwable yang hanya ada ketika masuk onFailure
                Toast.makeText(this,"Kesalahan : ${it.t?.localizedMessage}",Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun goToLandingPage() {
        val goToLoginIntent = Intent(this, RegisterActivity::class.java)
        startActivity(goToLoginIntent)
        finish()
    }

    private fun goToMenuActivity(data: AuthResponse.Data) {
        val intent = Intent(this, MenuActivity::class.java)
        intent.putExtra("dataFromAuth", data)
        startActivity(intent)
        finish()
    }

    fun onAuthLoginFailed(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        MySharedPreferences(this).deleteData()
    }

    fun goToLoginActivity() {
        val loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)
        finish()
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        viewModel.dispose()
//    }

}
