package com.example.neostore.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.contentValuesOf
class SharedPreferencesHelper(private val context: Context) {
    companion object {
        private const val PREF_NAME = "myPref"
        private const val ACCESS_TOKEN = "accessToken"
    }

    val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun saveData(value: String) {
        editor.putString(ACCESS_TOKEN, value)
        editor.apply()
    }

    fun getData(): String?{
        return sharedPreferences.getString(ACCESS_TOKEN, null)
    }

    fun clearData(){
        editor.remove(ACCESS_TOKEN)
        editor.apply()
    }
}