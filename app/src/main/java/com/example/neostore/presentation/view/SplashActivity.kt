package com.example.neostore.presentation.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.navigation.findNavController
import com.example.neostore.R
import com.example.neostore.utils.SharedPreferencesHelper

class SplashActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sharedPreferences = SharedPreferencesHelper(this)
        val access_token = sharedPreferences.getData()
        Log.d("SplashDebug", "Access Token: '$access_token'")

        Handler(Looper.getMainLooper()).postDelayed({
            if (!access_token.isNullOrBlank()) {
                Log.d("SplashDebug", "Redirecting to UserDataFragment")
                startActivity(Intent(this, UserDataActivity::class.java))
            } else {
                Log.d("SplashDebug", "Redirecting to MainActivity")
                startActivity(Intent(this, MainActivity::class.java))
            }
            finish()
        }, 2000)
    }
}
