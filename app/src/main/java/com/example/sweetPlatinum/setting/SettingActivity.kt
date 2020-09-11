package com.example.sweetPlatinum.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sweetplatinum.R

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.setting_holder, SettingsFragment())
            .commit()
        supportActionBar?.title = getString(R.string.setting_title)
    }
}