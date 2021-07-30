package com.harismexis.breakingbad.presentation.screens.quotes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.databinding.FragmentQuotesBinding
import com.harismexis.breakingbad.framework.util.event.EventObserver
import com.harismexis.breakingbad.framework.extensions.setDivider
import com.harismexis.breakingbad.framework.extensions.showToast
import com.harismexis.breakingbad.model.domain.Quote
import com.harismexis.breakingbad.presentation.base.BaseFragment
import com.harismexis.breakingbad.model.result.QuotesResult
import com.harismexis.breakingbad.presentation.screens.quotes.ui.adapter.QuoteAdapter
import com.harismexis.breakingbad.presentation.screens.quotes.viewmodel.QuotesViewModel

class QuotesFragment : BaseFragment() {

    private val viewModel: QuotesViewModel by viewModels { viewModelFactory }
    private var binding: FragmentQuotesBinding? = null
    private lateinit var adapter: QuoteAdapter
    private var uiModels: MutableList<Quote> = mutableListOf()

    companion object {

        private const val ARG_SERIES_NAME = "series_name"

        fun newInstance(seriesName: String): QuotesFragment {
            val args = Bundle()
            args.putString(ARG_SERIES_NAME, seriesName)
            return QuotesFragment().apply {
                arguments = args
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.seriesName = arguments?.getString(ARG_SERIES_NAME)
    }

    override fun initialiseViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = FragmentQuotesBinding.inflate(inflater, container, false)
    }

    override fun onCreateView() {
        setupSwipeToRefresh()
        initialiseRecycler()
    }

    override fun onViewCreated() {
        observeLiveData()
        viewModel.fetchQuotes()
    }

    private fun setupSwipeToRefresh() {
        binding?.swipeRefresh?.setOnRefreshListener {
            binding?.swipeRefresh?.isRefreshing = true
            viewModel.fetchQuotes()
        }
    }

    private fun initialiseRecycler() {
        adapter = QuoteAdapter(uiModels)
        adapter.setHasStableIds(true)
        binding?.let {
            it.list.layoutManager = LinearLayoutManager(this.context)
            it.list.adapter = adapter
            it.list.setDivider(R.drawable.divider)
        }
    }

    private fun observeLiveData() {
        viewModel.quotes.observe(viewLifecycleOwner, {
            when (it) {
                is QuotesResult.Success -> populate(it.items)
                is QuotesResult.Error -> {
                }
            }
        })

        viewModel.showErrorMessage.observe(viewLifecycleOwner, EventObserver {
            requireContext().showToast(it)
        })
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

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun getRootView() = binding?.root

}