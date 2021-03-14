package com.example.breakingbad.presentation.screens.quotes.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breakingbad.R
import com.example.breakingbad.databinding.FragmentQuotesBinding
import com.example.breakingbad.domain.Quote
import com.example.breakingbad.framework.base.BaseFragment
import com.example.breakingbad.presentation.screens.quotes.ui.adapter.QuoteAdapter
import com.example.breakingbad.presentation.screens.quotes.viewmodel.QuotesViewModel

class QuotesFragment : BaseFragment() {

    private lateinit var viewModel: QuotesViewModel
    private var binding: FragmentQuotesBinding? = null
    private lateinit var adapter: QuoteAdapter
    private var uiModels: MutableList<Quote> = mutableListOf()

    override fun onViewCreated() {
        observeLiveData()
        viewModel.bind()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun initialiseViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[QuotesViewModel::class.java]
    }

    override fun initialiseViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = FragmentQuotesBinding.inflate(inflater, container, false)
    }

    override fun getRootView() = binding?.root

    override fun initialiseView() {
        setupToolbar()
        setupSwipeToRefresh()
        initialiseRecycler()
    }

    override fun observeLiveData() {
        viewModel.models.observe(viewLifecycleOwner, {
            populate(it)
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
        adapter = QuoteAdapter(uiModels)
        adapter.setHasStableIds(true)
        binding?.let {
            it.list.layoutManager = LinearLayoutManager(this.context)
            it.list.adapter = adapter
        }
    }

    private fun populate(models: List<Quote>) {
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