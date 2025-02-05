package com.example.neostore.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.core.content.ContextCompat
import com.example.neostore.R
import com.example.neostore.databinding.ActivityUserDataBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserDataBinding
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        
    }
}