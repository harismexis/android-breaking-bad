package com.harismexis.breakingbad.presentation.screens.deaths.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.databinding.FragmentDeathsBinding
import com.harismexis.breakingbad.domain.Death
import com.harismexis.breakingbad.presentation.base.BaseFragment
import com.harismexis.breakingbad.framework.event.EventObserver
import com.harismexis.breakingbad.framework.extensions.setDivider
import com.harismexis.breakingbad.framework.extensions.showToast
import com.harismexis.breakingbad.presentation.result.DeathsResult
import com.harismexis.breakingbad.presentation.screens.deaths.ui.adapter.DeathAdapter
import com.harismexis.breakingbad.presentation.screens.deaths.viewmodel.DeathsViewModel

class DeathsFragment : BaseFragment() {

    private val viewModel: DeathsViewModel by viewModels { viewModelFactory }
    private var binding: FragmentDeathsBinding? = null
    private lateinit var adapter: DeathAdapter
    private var uiModels: MutableList<Death> = mutableListOf()

    override fun initialiseViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = FragmentDeathsBinding.inflate(inflater, container, false)
    }

    override fun onCreateView() {
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
            it.toolbarTitle.text = getString(R.string.screen_deaths_label)
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
        adapter = DeathAdapter(uiModels)
        adapter.setHasStableIds(true)
        binding?.let {
            it.list.layoutManager = LinearLayoutManager(this.context)
            it.list.adapter = adapter
            it.list.setDivider(R.drawable.divider)
        }
    }

    override fun onViewCreated() {
        observeLiveData()
        viewModel.fetchDeaths()
    }

    private fun observeLiveData() {
        viewModel.deaths.observe(viewLifecycleOwner, {
            when (it) {
                is DeathsResult.DeathsSuccess -> populate(it.items)
                is DeathsResult.DeathsError -> {}
            }
        })

        viewModel.showErrorMessage.observe(viewLifecycleOwner, EventObserver {
            requireContext().showToast(it)
        })
    }

    private fun populate(models: List<Death>) {
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