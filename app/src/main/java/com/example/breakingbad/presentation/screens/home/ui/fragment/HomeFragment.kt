package com.example.breakingbad.presentation.screens.home.ui.fragment

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breakingbad.R
import com.example.breakingbad.databinding.FragmentHomeBinding
import com.example.breakingbad.domain.Actor
import com.example.breakingbad.presentation.result.ActorsResult
import com.example.breakingbad.framework.base.BaseFragment
import com.example.breakingbad.framework.extensions.showToast
import com.example.breakingbad.presentation.screens.home.ui.adapter.ActorAdapter
import com.example.breakingbad.presentation.screens.home.ui.viewholder.ActorViewHolder
import com.example.breakingbad.presentation.screens.home.viewmodel.HomeViewModel

class HomeFragment : BaseFragment(), ActorViewHolder.ActorClickListener,
    android.widget.SearchView.OnQueryTextListener {

    private lateinit var viewModel: HomeViewModel
    private var binding: FragmentHomeBinding? = null
    private lateinit var adapter: ActorAdapter
    private var uiModels: MutableList<Actor> = mutableListOf()

    override fun onViewCreated() {
        setupToolbar()
        observeLiveData()
        viewModel.bind()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun setupToolbar() {
        val navController = findNavController()
        val appBarConf = AppBarConfiguration(navController.graph)
        binding?.let { it ->
            it.toolbar.setupWithNavController(navController, appBarConf)
            it.toolbar.inflateMenu(R.menu.menu_home)
            it.toolbar.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.quotes_dest -> {
                        navController.navigate(R.id.player_dest)
                        true
                    }
                    else -> false
                }
            }
        }
    }

    override fun initialiseViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[HomeViewModel::class.java]
    }

    override fun initialiseViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun getRootView() = binding?.root

    override fun initialiseView() {
        setupSwipeToRefresh()
        initialiseRecycler()
        initialiseSearchView()
    }

    override fun observeLiveData() {
        viewModel.actorsResult.observe(viewLifecycleOwner, {
            when (it) {
                is ActorsResult.ActorsSuccess -> populate(it.items)
                is ActorsResult.ActorsError -> populateError(it.error)
            }
        })
    }

    private fun populateError(error: String) {
        binding?.homeSwipeRefresh?.isRefreshing = false
        binding?.loadingProgressBar?.visibility = View.GONE
        requireContext().showToast(error)
    }

    override fun onActorClick(
        item: Actor,
        position: Int
    ) {
        binding?.searchView?.clearFocus()
        val action = HomeFragmentDirections.actionHomeDestToActorDetailDest(item.char_id)
        findNavController().navigate(action)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        viewModel.searchQuery = query
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        return false
    }

    private fun setupSwipeToRefresh() {
        binding?.homeSwipeRefresh?.setOnRefreshListener {
            binding?.homeSwipeRefresh?.isRefreshing = true
            viewModel.refresh { canRefresh ->
                if (!canRefresh) {
                    binding?.homeSwipeRefresh?.isRefreshing = false
                }
            }
        }
    }

    private fun initialiseRecycler() {
        adapter = ActorAdapter(uiModels, this)
        adapter.setHasStableIds(true)
        binding?.homeList?.layoutManager = LinearLayoutManager(this.context)
        binding?.homeList?.adapter = adapter
    }

    private fun initialiseSearchView() {
        binding?.searchView?.setOnQueryTextListener(this)
    }

    private fun populate(models: List<Actor>) {
        binding?.homeSwipeRefresh?.isRefreshing = false
        binding?.loadingProgressBar?.visibility = View.GONE
        binding?.homeList?.visibility = View.VISIBLE
        uiModels.clear()
        uiModels.addAll(models)
        adapter.notifyDataSetChanged()
    }

}