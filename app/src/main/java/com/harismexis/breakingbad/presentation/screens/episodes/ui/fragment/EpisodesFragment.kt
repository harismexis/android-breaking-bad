package com.harismexis.breakingbad.presentation.screens.episodes.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.databinding.FragmentEpisodesBinding
import com.harismexis.breakingbad.domain.Episode
import com.harismexis.breakingbad.framework.base.BaseFragment
import com.harismexis.breakingbad.framework.extensions.setDivider
import com.harismexis.breakingbad.framework.extensions.showToast
import com.harismexis.breakingbad.presentation.result.EpisodesResult
import com.harismexis.breakingbad.presentation.screens.episodes.ui.adapter.EpisodeAdapter
import com.harismexis.breakingbad.presentation.screens.episodes.viewmodel.EpisodesViewModel

class EpisodesFragment : BaseFragment() {

    private val viewModel: EpisodesViewModel by viewModels { viewModelFactory }
    private var binding: FragmentEpisodesBinding? = null
    private lateinit var adapter: EpisodeAdapter
    private var uiModels: MutableList<Episode> = mutableListOf()

    override fun initialiseViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = FragmentEpisodesBinding.inflate(inflater, container, false)
    }

    override fun initialiseView() {
        setupToolbar()
        setupSwipeToRefresh()
        initialiseRecycler()
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

    private fun setupSwipeToRefresh() {
        binding?.swipeRefresh?.setOnRefreshListener {
            binding?.swipeRefresh?.isRefreshing = true
            viewModel.refresh { canRefresh ->
                if (!canRefresh) {
                    binding?.swipeRefresh?.isRefreshing = false
                }
            }
        }
    }

    private fun initialiseRecycler() {
        adapter = EpisodeAdapter(uiModels)
        adapter.setHasStableIds(true)
        binding?.let {
            it.list.layoutManager = LinearLayoutManager(this.context)
            it.list.adapter = adapter
            it.list.setDivider(R.drawable.divider)
        }
    }

    override fun onViewCreated() {
        observeLiveData()
        viewModel.bind()
    }

    override fun observeLiveData() {
        viewModel.episodes.observe(viewLifecycleOwner, {
            when (it) {
                is EpisodesResult.EpisodesSuccess -> populate(it.items)
                is EpisodesResult.EpisodesError -> requireContext().showToast(it.error)
            }
        })
    }

    private fun populate(models: List<Episode>) {
        binding?.let {
            it.swipeRefresh.isRefreshing = false
            it.progressBar.visibility = View.GONE
            it.list.visibility = View.VISIBLE
        }
        uiModels.clear()
        uiModels.addAll(models)
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun getRootView() = binding?.root

}