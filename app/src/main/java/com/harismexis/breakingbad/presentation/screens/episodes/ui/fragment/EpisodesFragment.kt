package com.harismexis.breakingbad.presentation.screens.episodes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.databinding.FragmentEpisodesBinding
import com.harismexis.breakingbad.domain.Episode
import com.harismexis.breakingbad.framework.base.BaseFragment
import com.harismexis.breakingbad.framework.event.EventObserver
import com.harismexis.breakingbad.framework.extensions.setDivider
import com.harismexis.breakingbad.framework.extensions.showToast
import com.harismexis.breakingbad.presentation.result.EpisodesResult
import com.harismexis.breakingbad.presentation.screens.episodes.ui.adapter.EpisodeAdapter
import com.harismexis.breakingbad.presentation.screens.episodes.viewmodel.EpisodesViewModel

class EpisodesFragment : BaseFragment() {

    companion object {

        private const val ARG_SERIES_NAME = "series_name"

        fun newInstance(seriesName: String): EpisodesFragment {
            val args = Bundle()
            args.putString(ARG_SERIES_NAME, seriesName)
            return EpisodesFragment().apply {
                arguments = args
            }
        }
    }

    private val viewModel: EpisodesViewModel by viewModels { viewModelFactory }
    private var binding: FragmentEpisodesBinding? = null
    private lateinit var adapter: EpisodeAdapter
    private var uiModels: MutableList<Episode> = mutableListOf()
    private var seriesName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        seriesName = arguments?.getString(ARG_SERIES_NAME)
    }

    override fun onCreateView() {
        setupSwipeToRefresh()
        initialiseRecycler()
    }

    override fun initialiseViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = FragmentEpisodesBinding.inflate(inflater, container, false)
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
        adapter = EpisodeAdapter(uiModels)
        adapter.setHasStableIds(true)
        binding?.let {
            it.list.layoutManager = LinearLayoutManager(this.context)
            it.list.adapter = adapter
            it.list.setDivider(R.drawable.divider)
        }
    }

    override fun onViewCreated() {
        observeLiveData()
        viewModel.fetchEpisodes(seriesName)
    }

    private fun observeLiveData() {
        viewModel.episodes.observe(viewLifecycleOwner, {
            when (it) {
                is EpisodesResult.EpisodesSuccess -> populate(it.items)
                is EpisodesResult.EpisodesError -> {
                }
            }
        })

        viewModel.showErrorMessage.observe(viewLifecycleOwner, EventObserver {
            requireContext().showToast(it)
        })
    }

    private fun populate(models: List<Episode>) {
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