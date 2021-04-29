package com.harismexis.breakingbad.presentation.screens.home.ui.fragment

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.databinding.FragmentHomeBinding
import com.harismexis.breakingbad.datamodel.domain.Actor
import com.harismexis.breakingbad.framework.event.EventObserver
import com.harismexis.breakingbad.framework.extensions.showToast
import com.harismexis.breakingbad.framework.util.mapNewMexicoIntent
import com.harismexis.breakingbad.framework.util.ui.hideKeyboard
import com.harismexis.breakingbad.presentation.base.BaseFragment
import com.harismexis.breakingbad.presentation.result.ActorsResult
import com.harismexis.breakingbad.presentation.screens.home.ui.adapter.ActorAdapter
import com.harismexis.breakingbad.presentation.screens.home.ui.viewholder.ActorViewHolder
import com.harismexis.breakingbad.presentation.screens.home.viewmodel.HomeViewModel

class HomeFragment : BaseFragment(),
    ActorViewHolder.ActorClickListener,
    android.widget.SearchView.OnQueryTextListener,
    NavigationView.OnNavigationItemSelectedListener {

    private val viewModel: HomeViewModel by viewModels { viewModelFactory }
    private var binding: FragmentHomeBinding? = null
    private lateinit var adapter: ActorAdapter
    private var uiModels: MutableList<Actor> = mutableListOf()

    companion object {
        const val BREAKING_BAD_API_WEBSITE = "https://breakingbadapi.com/"
    }

    override fun initialiseViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onCreateView() {
        setupToolbar()
        setupSwipeToRefresh()
        initialiseRecycler()
        initialiseSearchView()
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
        binding?.homeList?.let {
            it.layoutManager = LinearLayoutManager(this.context)
            it.adapter = adapter
        }
    }

    private fun initialiseSearchView() {
        binding?.searchView?.setOnQueryTextListener(this)
    }

    override fun onViewCreated() {
        observeLiveData()
        viewModel.fetchInitialActors()
    }

    private fun setupToolbar() {
        val navController = findNavController()
        val appBarConf = AppBarConfiguration(navController.graph, binding?.homeDrawerLayout)
        binding?.apply { ->
            toolbar.apply {
                setupWithNavController(navController, appBarConf)
                inflateMenu(R.menu.menu_home)
                // Without listener it's not working, but it should(?)
                // as we call setupWithNavController
                setOnMenuItemClickListener { item ->
                    item.onNavDestinationSelected(findNavController())
                    true
                }
            }
            navView.setupWithNavController(navController)
            navView.setNavigationItemSelectedListener(this@HomeFragment)
        }
    }

    private fun observeLiveData() {
        viewModel.actorsResult.observe(viewLifecycleOwner, {
            when (it) {
                is ActorsResult.ActorsSuccess -> populate(it.items)
                is ActorsResult.ActorsError -> populateError(it.error)
            }
        })

        viewModel.showErrorMessage.observe(viewLifecycleOwner, EventObserver {
            requireContext().showToast(it)
        })
    }

    private fun populate(models: List<Actor>) {
        binding?.homeSwipeRefresh?.isRefreshing = false
        binding?.loadingProgressBar?.visibility = View.GONE
        binding?.homeList?.visibility = View.VISIBLE
        uiModels.clear()
        uiModels.addAll(models)
        adapter.notifyDataSetChanged()
    }

    private fun populateError(error: Exception) {
        binding?.homeSwipeRefresh?.isRefreshing = false
        binding?.loadingProgressBar?.visibility = View.GONE
    }

    override fun onActorClick(
        item: Actor,
        position: Int
    ) {
        binding?.searchView?.clearFocus()
        val action = HomeFragmentDirections.actionHomeDestToActorDetailDest(item.actorId)
        findNavController().navigate(action)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        requireActivity().hideKeyboard()
        viewModel.updateSearchQuery(query)
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        return false
    }

    override fun getRootView() = binding?.root

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.map_dest -> {
                startActivity(mapNewMexicoIntent())
            }
            R.id.doc_dest -> {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(BREAKING_BAD_API_WEBSITE)
                )
                startActivity(browserIntent)
            }
            else -> item.onNavDestinationSelected(findNavController())
        }
        closeDrawer()
        return true
    }

    private fun closeDrawer() {
        binding?.homeDrawerLayout?.closeDrawer(GravityCompat.START)
    }

}