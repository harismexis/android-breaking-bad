package com.harismexis.breakingbad.presentation.screens.episodes.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.harismexis.breakingbad.presentation.base.BasePagerAdapter
import com.harismexis.breakingbad.presentation.screens.episodes.ui.fragment.EpisodesFragment

class EpisodesPagerAdapter(
    activity: AppCompatActivity,
    itemsCount: Int
) : BasePagerAdapter(activity, itemsCount) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> EpisodesFragment.newInstance(series[0])
            else -> EpisodesFragment.newInstance(series[1])
        }
    }

}