package com.harismexis.breakingbad.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.databinding.FragmentViewPagerContainerBinding

abstract class BaseViewPagerFragment : Fragment() {

    private var binding: FragmentViewPagerContainerBinding? = null
    private var tabNames = arrayOf("Breaking Bad", "Better Call Saul")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewPagerContainerBinding.inflate(inflater, container, false)
        setupToolbar()
        setupViewPager()
        return binding?.root
    }

    private fun setupViewPager() {
        // val episodesPagerAdapter = EpisodesPagerAdapter(requireActivity() as AppCompatActivity, 2)
        val pagerAdapter = getPageAdapter()
        binding?.apply {
            pager.adapter = pagerAdapter
            pager.registerOnPageChangeCallback(tabSelectionCallback)
            TabLayoutMediator(tabLayout, pager) { tab, position ->
                tab.text = tabNames[position]
            }.attach()
        }
    }

    private var tabSelectionCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) { }
    }

    private fun setupToolbar() {
        val navController = findNavController()
        val appBarConf = AppBarConfiguration(navController.graph)
        binding?.let {
            it.toolbar.setupWithNavController(navController, appBarConf)
            it.toolbar.setNavigationIcon(R.drawable.ic_arrow_left_white_rounded_24dp)
            it.toolbarTitle.text = getToolbarTitle()
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    abstract fun getPageAdapter(): FragmentStateAdapter

    abstract fun getToolbarTitle(): String
}