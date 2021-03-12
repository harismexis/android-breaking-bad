package com.example.breakingbad.presentation.home.ui.activity

import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breakingbad.databinding.ActivityHomeBinding
import com.example.breakingbad.framework.base.BaseActivity
import com.example.breakingbad.presentation.bbcharacterdetail.ui.BBCharacterDetailActivity.Companion.startCatDetailActivity
import com.example.breakingbad.domain.BBCharacter
import com.example.breakingbad.presentation.home.ui.adapter.BBCharacterAdapter
import com.example.breakingbad.presentation.home.ui.viewholder.BBCharacterViewHolder
import com.example.breakingbad.presentation.home.viewmodel.HomeViewModel

class HomeActivity : BaseActivity(), BBCharacterViewHolder.BBCharacterClickListener {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: BBCharacterAdapter
    private var uiModels: MutableList<BBCharacter> = mutableListOf()

    override fun initialise() {
        super.initialise()
        setupSwipeToRefresh()
        viewModel.bind()
    }

    override fun initialiseViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[HomeViewModel::class.java]
    }

    override fun initialiseViewBinding() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun initialiseView() {
        super.initialiseView()
        initialiseRecycler()
    }

    override fun getRootView(): View {
        return binding.root
    }

    override fun onBBCharacterClick(item: BBCharacter, position: Int) {
        startCatDetailActivity(item.id)
    }

    override fun getToolbar(): Toolbar {
        return binding.homeToolbar
    }

    override fun observeLiveData() {
        viewModel.models.observe(this, {
            populate(it)
        })
    }

    private fun populate(models: List<BBCharacter>) {
        binding.homeSwipeRefresh.isRefreshing = false
        binding.loadingProgressBar.visibility = View.GONE
        binding.homeList.visibility = View.VISIBLE
        uiModels.clear()
        uiModels.addAll(models)
        adapter.notifyDataSetChanged()
    }

    private fun initialiseRecycler() {
        adapter = BBCharacterAdapter(uiModels, this)
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