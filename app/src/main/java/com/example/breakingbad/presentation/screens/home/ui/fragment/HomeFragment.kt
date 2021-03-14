package com.example.breakingbad.presentation.screens.home.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breakingbad.R
import com.example.breakingbad.databinding.FragmentHomeBinding
import com.example.breakingbad.domain.Actor
import com.example.breakingbad.framework.base.BaseFragment
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
                        navController.navigate(R.id.quotes_dest)
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
        viewModel.models.observe(viewLifecycleOwner, {
            populate(it)
        })
    }

    override fun onActorClick(
        item: Actor,
        position: Int
    ) {
        binding?.searchView?.clearFocus()
        //requireContext().startActorActivity(item.char_id)
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