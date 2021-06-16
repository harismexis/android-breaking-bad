package com.harismexis.breakingbad.presentation.base

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.harismexis.breakingbad.R

abstract class BasePagerAdapter(
    activity: AppCompatActivity,
    private val itemsCount: Int
) : FragmentStateAdapter(activity) {

    protected val series = arrayOf(
        activity.getString(R.string.breaking_bad),
        activity.getString(R.string.better_call_saul))

    override fun getItemCount(): Int {
        return itemsCount
    }

}