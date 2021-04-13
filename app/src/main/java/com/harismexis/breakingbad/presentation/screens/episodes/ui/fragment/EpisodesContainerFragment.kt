package com.harismexis.breakingbad.presentation.screens.episodes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.databinding.FragmentEpisodesContainerBinding

class EpisodesContainerFragment : Fragment() {

    private var binding: FragmentEpisodesContainerBinding? = null
    private var tabNames = arrayOf("Breaking Bad", "Better call Saul")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEpisodesContainerBinding.inflate(inflater, container, false)
        setupToolbar()
        setupViewPager()
        return binding?.root
    }

    private fun setupViewPager() {
        val episodesPagerAdapter = EpisodesPagerAdapter(requireActivity() as AppCompatActivity, 2)
        binding?.apply {
            pager.adapter = episodesPagerAdapter
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
            it.toolbarTitle.text = getString(R.string.screen_episodes_label)
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

}