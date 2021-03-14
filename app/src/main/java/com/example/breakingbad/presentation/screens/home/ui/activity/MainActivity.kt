package com.example.breakingbad.presentation.screens.home.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.breakingbad.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}