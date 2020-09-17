package com.example.sweetPlatinum.setting

import android.app.TimePickerDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings
import android.widget.TimePicker
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreference
import com.example.sweetPlatinum.R
import java.text.SimpleDateFormat
import java.util.*

class SettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceClickListener,
    TimePickerDialog.OnTimeSetListener, SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var simpleDateFormat: SimpleDateFormat

    private var languagePreference: Preference? = null
    private var reminderSwitch: SwitchPreference? = null
    private var reminderTimePreference: Preference? = null
    private var hours = 0
    private var minutes = 0
    private lateinit var timePickerDialog: TimePickerDialog

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting_preferences, rootKey)
        init()
        setSummaries()
        setReminderSwitch()
    }

    private fun setReminderSwitch() {
        when (sharedPreferences.getBoolean("reminder_switch", false)) {
            true -> reminderTimePreference?.isVisible = true
            false -> reminderTimePreference?.isVisible = false
        }
    }

    private fun init() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        reminderSwitch = findPreference("reminder_switch")
        reminderTimePreference = findPreference("reminder_time")
        languagePreference = findPreference("language")
        languagePreference?.onPreferenceClickListener = this
        reminderTimePreference?.onPreferenceClickListener = this
        simpleDateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    }

    override fun onPreferenceClick(preference: Preference?): Boolean {
        when (preference?.key) {
            "language" -> {
                val changeIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(changeIntent)
            }
            "reminder_time" -> {
                timePickerDialog = TimePickerDialog(context, this, hours, minutes, true)
                timePickerDialog.show()
            }
        }
        return true
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        hours = hourOfDay
        minutes = minute
        setSummaries()
        AlarmReceiver().setReminder(requireContext())
    }

    private fun setSummaries() {
        reminderTimePreference?.summary =
            simpleDateFormat.format(getHourAnMinute())
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            "reminder_switch" -> {
                when (sharedPreferences?.getBoolean("reminder_switch", false)) {
                    true -> {
                        reminderTimePreference?.isVisible = true
                        AlarmReceiver().setReminder(requireContext())
                    }
                    false -> {
                        reminderTimePreference?.isVisible = false
                        AlarmReceiver().cancelReminder(requireContext())
                    }
                }
            }
        }
    }

    private fun getHourAnMinute() : Date {
        val calendar = Calendar.getInstance().apply {
            set(0, 0, 0, 9, 0)
        }
        return calendar.time
    }
}