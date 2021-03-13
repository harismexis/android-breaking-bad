package com.example.breakingbad.presentation.screens.quotes.ui.fragment

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.breakingbad.R

class QuoteActivity : AppCompatActivity() {

    companion object {

        fun Context.startQuoteActivity() {
            this.startActivity(createIntent(this))
        }

        private fun createIntent(
            context: Context
        ): Intent {
            return Intent(context, QuoteActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.quotes_container, QuoteFragment())
        transaction.commit()
    }
}