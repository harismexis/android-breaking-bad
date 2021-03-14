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
import com.example.breakingbad.databinding.FragmentQuoteBinding
import com.example.breakingbad.domain.Quote
import com.example.breakingbad.framework.base.BaseFragment
import com.example.breakingbad.presentation.screens.quotes.ui.adapter.QuoteAdapter
import com.example.breakingbad.presentation.screens.quotes.viewmodel.QuoteViewModel

class QuoteFragment : BaseFragment() {

    private lateinit var viewModel: QuoteViewModel
    private var binding: FragmentQuoteBinding? = null
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
        viewModel = ViewModelProviders.of(this, viewModelFactory)[QuoteViewModel::class.java]
    }

    override fun initialiseViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = FragmentQuoteBinding.inflate(inflater, container, false)
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
        adapter = QuoteAdapter(uiModels)
        adapter.setHasStableIds(true)
        binding?.let {
            it.quoteList.layoutManager = LinearLayoutManager(this.context)
            it.quoteList.adapter = adapter
        }
    }

    private fun populate(models: List<Quote>) {
        binding?.let {
            it.homeSwipeRefresh.isRefreshing = false
            it.loadingProgressBar.visibility = View.GONE
            it.quoteList.visibility = View.VISIBLE
        }
        uiModels.clear()
        uiModels.addAll(models)
        adapter.notifyDataSetChanged()
    }

}