package com.harismexis.breakingbad.presentation.screens.quotes.ui.fragment

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class QuotesPagerAdapter(
    activity: AppCompatActivity,
    private val itemsCount: Int
) : FragmentStateAdapter(activity) {

    private val series = arrayOf("Breaking Bad", "Better Call Saul")

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> QuotesFragment.newInstance(series[0])
            else -> QuotesFragment.newInstance(series[1])
        }
    }

    override fun getItemCount(): Int {
        return itemsCount
    }

}