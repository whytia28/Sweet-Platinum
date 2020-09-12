package com.example.sweetPlatinum.setting

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.sweetplatinum.R

class SettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceClickListener {

    private var languagePreference: Preference? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting_preferences, rootKey)
        init()
    }

    private fun init() {
        languagePreference = findPreference("language")
        languagePreference?.onPreferenceClickListener = this
    }

    override fun onPreferenceClick(preference: Preference?): Boolean {
        when(preference?.key) {
            "language" -> {
                val changeIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(changeIntent)
            }
        }
        return true
    }
}