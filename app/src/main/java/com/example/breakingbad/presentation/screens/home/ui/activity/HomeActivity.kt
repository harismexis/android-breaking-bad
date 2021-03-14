package com.example.breakingbad.presentation.screens.home.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.breakingbad.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}