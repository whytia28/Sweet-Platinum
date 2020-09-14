package com.example.sweetPlatinum.sharedPreference

import android.content.Context

class MySharedPreferences(val context: Context) {

    private var mySharedPreferences = context.getSharedPreferences("userData", Context.MODE_PRIVATE)

    fun putData(key: String, data: String) {
        mySharedPreferences.edit().putString(key, data).apply()
    }

    fun getData(key: String): String? {
        return mySharedPreferences.getString(key, "")
    }

    fun deleteData() {
        val editor = mySharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}