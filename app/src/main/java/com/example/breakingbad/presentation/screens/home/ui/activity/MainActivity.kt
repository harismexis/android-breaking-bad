package com.example.breakingbad.presentation.screens.home.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.breakingbad.R
import com.example.breakingbad.presentation.screens.home.ui.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, HomeFragment())
        transaction.commit()
    }

}