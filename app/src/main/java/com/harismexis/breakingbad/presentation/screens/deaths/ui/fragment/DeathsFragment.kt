package com.harismexis.breakingbad.presentation.screens.deaths.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.core.domain.Death
import com.harismexis.breakingbad.core.result.DeathsResult
import com.harismexis.breakingbad.databinding.FragmentDeathsBinding
import com.harismexis.breakingbad.framework.util.event.EventObserver
import com.harismexis.breakingbad.framework.util.extensions.setDivider
import com.harismexis.breakingbad.framework.util.extensions.showToast
import com.harismexis.breakingbad.presentation.base.BaseFragment
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
        binding?.apply {
            toolbar.apply {
                setupWithNavController(navController, appBarConf)
                inflateMenu(R.menu.menu_deaths)
                // Without listener it's not working, but it should(?)
                // as we call setupWithNavController
                setOnMenuItemClickListener { item ->
                    item.onNavDestinationSelected(findNavController())
                    true
                }
                // Setting icon in xml not working, still shows the default
                setNavigationIcon(R.drawable.ic_arrow_left_white_rounded_24dp)
                toolbarTitle.text = getString(R.string.screen_deaths_label)
            }
        }
    }

    private fun setupSwipeToRefresh() {
        binding?.swipeRefresh?.setOnRefreshListener {
            binding?.swipeRefresh?.isRefreshing = true
            viewModel.fetchDeaths()
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
                is DeathsResult.Success -> populate(it.items)
                is DeathsResult.Error -> {}
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