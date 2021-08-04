package com.harismexis.breakingbad.presentation.screens.deaths.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.core.domain.Death
import com.harismexis.breakingbad.core.result.DeathsResult
import com.harismexis.breakingbad.databinding.FragmentDeathsBinding
import com.harismexis.breakingbad.framework.util.event.EventObserver
import com.harismexis.breakingbad.framework.util.extensions.setDivider
import com.harismexis.breakingbad.framework.util.extensions.showSnackBar
import com.harismexis.breakingbad.presentation.base.BaseFragment
import com.harismexis.breakingbad.presentation.screens.deaths.ui.adapter.DeathAdapter
import com.harismexis.breakingbad.presentation.screens.deaths.viewmodel.DeathsViewModel
import com.harismexis.breakingbad.presentation.screens.player.ui.fragment.PlayerFragment

class DeathsFragment : BaseFragment() {

    private val viewModel: DeathsViewModel by viewModels { viewModelFactory }
    private var binding: FragmentDeathsBinding? = null
    private lateinit var adapter: DeathAdapter
    private val deaths = mutableListOf<Death>()

    override fun initialiseViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = FragmentDeathsBinding.inflate(inflater, container, false)
    }

    override fun onCreateView() {
        setupSwipeToRefresh() { viewModel.updateDeaths() }
        initialiseRecycler()
    }

    override fun getSwipeRefreshLayout(): SwipeRefreshLayout? {
        return binding?.swipeRefresh
    }

    private fun initialiseRecycler() {
        adapter = DeathAdapter(deaths)
        adapter.setHasStableIds(true)
        binding?.list?.let {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = adapter
            it.setDivider(R.drawable.divider)
        }
    }

    override fun onViewCreated() {
        setupToolbar()
        observeLiveData()
        viewModel.updateDeaths()
    }

    private fun setupToolbar() {
        val navController = findNavController()
        val appBarConf = AppBarConfiguration(navController.graph)
        binding?.apply {
            toolbar.apply {
                setupWithNavController(navController, appBarConf)
                inflateMenu(R.menu.menu_deaths)
                setOnMenuItemClickListener {
                    watchBreakingBadDeaths()
                    true
                }
                setNavigationIcon(R.drawable.ic_arrow_left_white_rounded_24dp)
                toolbarTitle.text = getString(R.string.screen_deaths_label)
            }
        }
    }

    private fun watchBreakingBadDeaths() {
        val args = Bundle()
        args.putString(PlayerFragment.ARG_VIDEO_ID, "zzyudpED6sg")
        findNavController().navigate(R.id.player_dest, args)
    }

    private fun observeLiveData() {
        viewModel.deaths.observe(viewLifecycleOwner, {
            when (it) {
                is DeathsResult.Success -> populate(it.items)
                is DeathsResult.Error -> {
                }
            }
        })

        viewModel.showErrorMsg.observe(viewLifecycleOwner, EventObserver {
            binding?.root?.showSnackBar(it)
        })
    }

    private fun populate(models: List<Death>) {
        binding?.let {
            it.swipeRefresh.isRefreshing = false
            it.progressBar.visibility = View.GONE
            it.list.visibility = View.VISIBLE
        }
        deaths.clear()
        deaths.addAll(models)
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun getRootView() = binding?.root

}