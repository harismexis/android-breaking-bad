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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.navigation.NavigationView
import com.harismexis.breakingbad.BuildConfig
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.core.domain.Actor
import com.harismexis.breakingbad.core.result.ActorsResult
import com.harismexis.breakingbad.databinding.FragmentHomeBinding
import com.harismexis.breakingbad.framework.util.event.EventObserver
import com.harismexis.breakingbad.framework.util.extensions.hideKeyboard
import com.harismexis.breakingbad.framework.util.extensions.showSnackBar
import com.harismexis.breakingbad.framework.util.googleMapsNewMexico
import com.harismexis.breakingbad.presentation.base.BaseDIFragment
import com.harismexis.breakingbad.presentation.screens.home.ui.adapter.ActorListAdapter
import com.harismexis.breakingbad.presentation.screens.home.ui.viewholder.ActorViewHolder
import com.harismexis.breakingbad.presentation.screens.home.viewmodel.HomeViewModel

class HomeFragment : BaseDIFragment(),
    ActorViewHolder.ActorClickListener,
    android.widget.SearchView.OnQueryTextListener,
    NavigationView.OnNavigationItemSelectedListener {

    private val viewModel: HomeViewModel by viewModels { viewModelFactory }
    private var binding: FragmentHomeBinding? = null
    private lateinit var adapter: ActorListAdapter

    override fun initialiseViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onCreateView() {
        setupSwipeToRefresh() { viewModel.updateActors() }
        initialiseRecycler()
        initialiseSearchView()
    }

    private fun initialiseRecycler() {
        adapter = ActorListAdapter(this)
        binding?.homeList?.let {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = adapter
        }
    }

    private fun initialiseSearchView() {
        binding?.searchView?.setOnQueryTextListener(this)
    }

    override fun onViewCreated() {
        setupToolbar()
        observeLiveData()
        viewModel.updateActors()
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
            navView.apply {
                setupWithNavController(navController)
                setNavigationItemSelectedListener(this@HomeFragment)
            }
        }
    }

    private fun observeLiveData() {
        viewModel.actors.observe(viewLifecycleOwner, {
            when (it) {
                is ActorsResult.Success -> populate(it.items)
                is ActorsResult.Error -> populateError(it.error)
            }
        })

        viewModel.showErrorMsg.observe(viewLifecycleOwner, EventObserver {
            binding?.homeCoordinator?.showSnackBar(it)
        })
    }

    private fun populate(items: List<Actor>) {
        binding?.let {
            it.swipeRefresh.isRefreshing = false
            it.loadingProgressBar.visibility = View.GONE
            val hasItems = items.isNotEmpty()
            val recyclerVisib = if (hasItems) View.VISIBLE else View.GONE
            val emptyViewVisib = if (hasItems) View.GONE else View.VISIBLE
            it.homeList.visibility = recyclerVisib
            it.noResults.visibility = emptyViewVisib
            adapter.submitList(items)
        }
    }

    private fun populateError(error: Exception) {
        binding?.let {
            it.swipeRefresh.isRefreshing = false
            it.loadingProgressBar.visibility = View.GONE
        }
    }

    override fun onActorClick(
        item: Actor,
        position: Int
    ) {
        binding?.searchView?.clearFocus()
        val action = HomeFragmentDirections.actionHomeDestToActorDetailDest(item.id)
        findNavController().navigate(action)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        requireActivity().hideKeyboard()
        viewModel.searchActors(query)
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        return false
    }

    override fun getRootView() = binding?.root

    override fun getSwipeRefreshLayout(): SwipeRefreshLayout? {
        return binding?.swipeRefresh
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.map_dest -> {
                startActivity(googleMapsNewMexico())
            }
            R.id.doc_dest -> {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(BuildConfig.BREAKING_BAD_API_WEBSITE_URL)
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