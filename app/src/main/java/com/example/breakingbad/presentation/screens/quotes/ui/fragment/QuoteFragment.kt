package com.example.breakingbad.presentation.screens.quotes.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
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
        binding?.let {
            it.quoteToolbar.setNavigationOnClickListener {
                requireActivity().finish()
            }
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
        binding?.quoteList?.layoutManager = LinearLayoutManager(this.context)
        binding?.quoteList?.adapter = adapter
    }

    private fun populate(models: List<Quote>) {
        binding?.homeSwipeRefresh?.isRefreshing = false
        binding?.loadingProgressBar?.visibility = View.GONE
        binding?.quoteList?.visibility = View.VISIBLE
        uiModels.clear()
        uiModels.addAll(models)
        adapter.notifyDataSetChanged()
    }

}