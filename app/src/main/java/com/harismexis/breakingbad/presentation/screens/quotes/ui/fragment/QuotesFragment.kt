package com.harismexis.breakingbad.presentation.screens.quotes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.core.domain.Quote
import com.harismexis.breakingbad.core.result.QuotesResult
import com.harismexis.breakingbad.databinding.FragmentQuotesBinding
import com.harismexis.breakingbad.framework.util.event.EventObserver
import com.harismexis.breakingbad.framework.util.extensions.setDivider
import com.harismexis.breakingbad.framework.util.extensions.showSnackBar
import com.harismexis.breakingbad.presentation.base.BaseDIFragment
import com.harismexis.breakingbad.presentation.screens.quotes.ui.adapter.QuoteAdapter
import com.harismexis.breakingbad.presentation.screens.quotes.viewmodel.QuotesViewModel

class QuotesFragment : BaseDIFragment() {

    private val viewModel: QuotesViewModel by viewModels { viewModelFactory }
    private var binding: FragmentQuotesBinding? = null
    private lateinit var adapter: QuoteAdapter
    private val quotes = mutableListOf<Quote>()

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
        setupSwipeToRefresh() { viewModel.updateQuotes() }
        initialiseRecycler()
    }

    override fun onViewCreated() {
        observeLiveData()
        viewModel.updateQuotes()
    }

    override fun getSwipeRefreshLayout(): SwipeRefreshLayout? {
        return binding?.swipeRefresh
    }

    private fun initialiseRecycler() {
        adapter = QuoteAdapter(quotes)
        adapter.setHasStableIds(true)
        binding?.list?.let {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = adapter
            it.setDivider(R.drawable.divider)
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

        viewModel.showErrorMsg.observe(viewLifecycleOwner, EventObserver {
            binding?.root?.showSnackBar(it)
        })
    }

    private fun populate(models: List<Quote>) {
        binding?.let {
            it.swipeRefresh.isRefreshing = false
            it.progressBar.visibility = View.GONE
            it.list.visibility = View.VISIBLE
        }
        quotes.clear()
        quotes.addAll(models)
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun getRootView() = binding?.root

}