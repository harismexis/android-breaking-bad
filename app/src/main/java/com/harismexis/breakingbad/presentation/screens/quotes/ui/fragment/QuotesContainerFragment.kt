package com.harismexis.breakingbad.presentation.screens.quotes.ui.fragment

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.presentation.base.BaseViewPagerFragment
import com.harismexis.breakingbad.presentation.screens.quotes.ui.adapter.QuotesPagerAdapter

class QuotesContainerFragment : BaseViewPagerFragment() {

    override fun getPageAdapter(): FragmentStateAdapter {
        return QuotesPagerAdapter(requireActivity() as AppCompatActivity, 2)
    }

    override fun getToolbarTitle(): String {
        return getString(R.string.screen_quotes_label)
    }

}