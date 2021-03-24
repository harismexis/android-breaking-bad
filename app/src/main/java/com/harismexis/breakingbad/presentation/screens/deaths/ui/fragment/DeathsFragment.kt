package com.harismexis.breakingbad.presentation.screens.deaths.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.harismexis.breakingbad.domain.Death
import com.harismexis.breakingbad.framework.base.BaseFragment
import com.harismexis.breakingbad.framework.extensions.showToast
import com.harismexis.breakingbad.presentation.result.DeathsResult
import com.harismexis.breakingbad.presentation.screens.deaths.ui.adapter.DeathAdapter
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.databinding.FragmentDeathsBinding
import com.harismexis.breakingbad.presentation.screens.deaths.viewmodel.DeathsViewModel

class DeathsFragment : BaseFragment() {

    private lateinit var viewModel: DeathsViewModel
    private var binding: FragmentDeathsBinding? = null
    private lateinit var adapter: DeathAdapter
    private var uiModels: MutableList<Death> = mutableListOf()

    override fun onViewCreated() {
        observeLiveData()
        viewModel.bind()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun initialiseViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[DeathsViewModel::class.java]
    }

    override fun initialiseViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = FragmentDeathsBinding.inflate(inflater, container, false)
    }

    override fun getRootView() = binding?.root

    override fun initialiseView() {
        setupToolbar()
        setupSwipeToRefresh()
        initialiseRecycler()
    }

    override fun observeLiveData() {
        viewModel.deaths.observe(viewLifecycleOwner, {
            when (it) {
                is DeathsResult.DeathsSuccess -> populate(it.items)
                is DeathsResult.DeathsError -> requireContext().showToast(it.error)
            }
        })
    }

    private fun setupToolbar() {
        val navController = findNavController()
        val appBarConf = AppBarConfiguration(navController.graph)
        binding?.let {
            it.toolbar.setupWithNavController(navController, appBarConf)
            it.toolbar.setNavigationIcon(R.drawable.ic_arrow_left_white_rounded_24dp)
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
        }
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

}