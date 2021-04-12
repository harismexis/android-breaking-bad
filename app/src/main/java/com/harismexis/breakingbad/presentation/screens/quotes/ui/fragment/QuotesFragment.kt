package com.harismexis.breakingbad.presentation.screens.quotes.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.databinding.FragmentQuotesBinding
import com.harismexis.breakingbad.domain.Quote
import com.harismexis.breakingbad.framework.base.BaseFragment
import com.harismexis.breakingbad.framework.event.EventObserver
import com.harismexis.breakingbad.framework.extensions.setDivider
import com.harismexis.breakingbad.framework.extensions.showToast
import com.harismexis.breakingbad.presentation.result.QuotesResult
import com.harismexis.breakingbad.presentation.screens.quotes.ui.adapter.QuoteAdapter
import com.harismexis.breakingbad.presentation.screens.quotes.viewmodel.QuotesViewModel

class QuotesFragment : BaseFragment() {

    private val viewModel: QuotesViewModel by viewModels { viewModelFactory }
    private var binding: FragmentQuotesBinding? = null
    private lateinit var adapter: QuoteAdapter
    private var uiModels: MutableList<Quote> = mutableListOf()

    override fun initialiseViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = FragmentQuotesBinding.inflate(inflater, container, false)
    }

    override fun initialiseView() {
        setupToolbar()
        setupSwipeToRefresh()
        initialiseRecycler()
    }

    override fun onViewCreated() {
        observeLiveData()
        viewModel.bind()
    }

    private fun setupToolbar() {
        val navController = findNavController()
        val appBarConf = AppBarConfiguration(navController.graph)
        binding?.let {
            it.toolbar.setupWithNavController(navController, appBarConf)
            it.toolbar.setNavigationIcon(R.drawable.ic_arrow_left_white_rounded_24dp)
            it.toolbarTitle.text = getString(R.string.screen_quotes_label)
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
            it.list.setDivider(R.drawable.divider)
        }
    }

    private fun observeLiveData() {
        viewModel.quotes.observe(viewLifecycleOwner, {
            when (it) {
                is QuotesResult.QuotesSuccess -> populate(it.items)
                is QuotesResult.QuotesError -> {}
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