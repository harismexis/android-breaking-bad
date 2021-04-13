package com.harismexis.breakingbad.presentation.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.harismexis.breakingbad.presentation.screens.episodes.ui.fragment.EpisodesFragment

abstract class BasePagerAdapter(
    activity: AppCompatActivity,
    private val itemsCount: Int
) : FragmentStateAdapter(activity) {

    private val series = arrayOf("Breaking Bad", "Better Call Saul")

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> EpisodesFragment.newInstance(series[0])
            else -> EpisodesFragment.newInstance(series[1])
        }
    }

    override fun getItemCount(): Int {
        return itemsCount
    }

}