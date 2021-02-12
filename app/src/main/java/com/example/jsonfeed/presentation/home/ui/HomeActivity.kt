package com.example.jsonfeed.presentation.home.ui

import android.view.View

import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.jsonfeed.databinding.ActivityHomeBinding
import com.example.jsonfeed.domain.Item
import com.example.jsonfeed.presentation.home.adapter.HomeAdapter
import com.example.jsonfeed.presentation.home.viewholder.FeedItemVh
import com.example.jsonfeed.framework.base.BaseActivity
import com.example.jsonfeed.presentation.detail.ui.ItemDetailActivity.Companion.startItemDetailActivity
import com.example.jsonfeed.presentation.home.viewmodel.HomeVm

class HomeActivity : BaseActivity(), FeedItemVh.FeedItemClickListener {

    private lateinit var viewModel: HomeVm
    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: HomeAdapter
    private var uiModels: MutableList<Item> = mutableListOf()

    override fun initialise() {
        super.initialise()
        setupSwipeToRefresh()
        viewModel.bind()
    }

    override fun initialiseViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[HomeVm::class.java]
    }

    override fun initialiseViewBinding() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun initialiseView() {
        initialiseRecycler()
    }

    override fun getRootView(): View {
        return binding.root
    }

    override fun onFeedItemClick(item: Item, position: Int) {
        startItemDetailActivity(item.id)
    }

    override fun getToolbar(): Toolbar {
        return binding.homeToolbar
    }

    override fun observeLiveData() {
        viewModel.models.observe(this, {
            populate(it)
        })
    }

    private fun populate(models: List<Item>) {
        binding.homeSwipeRefresh.isRefreshing = false
        binding.loadingProgressBar.visibility = View.GONE
        binding.homeList.visibility = View.VISIBLE
        uiModels.clear()
        uiModels.addAll(models)
        adapter.notifyDataSetChanged()
    }

    private fun initialiseRecycler() {
        adapter = HomeAdapter(uiModels, this)
        adapter.setHasStableIds(true)
        binding.homeList.layoutManager = LinearLayoutManager(this)
        binding.homeList.adapter = adapter
    }

    private fun setupSwipeToRefresh() {
        binding.homeSwipeRefresh.setOnRefreshListener {
            binding.homeSwipeRefresh.isRefreshing = true
            viewModel.refresh { canRefresh ->
                if (!canRefresh) {
                    binding.homeSwipeRefresh.isRefreshing = false
                }
            }
        }
    }

}