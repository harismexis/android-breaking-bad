package com.example.breakingbad.presentation.screens.home.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breakingbad.R
import com.example.breakingbad.databinding.FragmentHomeBinding
import com.example.breakingbad.domain.Actor
import com.example.breakingbad.framework.base.BaseFragment
import com.example.breakingbad.presentation.screens.actordetail.ui.activity.ActorActivity.Companion.startActorActivity
import com.example.breakingbad.presentation.screens.home.ui.adapter.ActorAdapter
import com.example.breakingbad.presentation.screens.home.ui.viewholder.ActorViewHolder
import com.example.breakingbad.presentation.screens.home.viewmodel.HomeViewModel
import com.example.breakingbad.presentation.screens.quotes.ui.fragment.QuoteActivity.Companion.startQuoteActivity

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
        binding?.let { it ->
            it.homeToolbar.inflateMenu(R.menu.menu_home)
            it.homeToolbar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.action_open_quotes -> {
                        requireContext().startQuoteActivity()
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
        requireContext().startActorActivity(item.char_id)
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