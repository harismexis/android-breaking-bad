package com.harismexis.breakingbad.presentation.screens.episodes.ui.fragment

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.presentation.base.BaseViewPagerFragment
import com.harismexis.breakingbad.presentation.screens.episodes.ui.adapter.EpisodesPagerAdapter

class EpisodesContainerFragment : BaseViewPagerFragment() {

    override fun getPageAdapter(): FragmentStateAdapter {
        return EpisodesPagerAdapter(requireActivity() as AppCompatActivity, 2)
    }

    override fun getToolbarTitle(): String {
        return getString(R.string.screen_episodes_label)
    }

}