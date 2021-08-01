package com.harismexis.breakingbad.presentation.screens.episodes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.core.domain.Episode
import com.harismexis.breakingbad.core.result.EpisodesResult
import com.harismexis.breakingbad.databinding.FragmentEpisodesBinding
import com.harismexis.breakingbad.framework.util.event.EventObserver
import com.harismexis.breakingbad.framework.util.extensions.setDivider
import com.harismexis.breakingbad.framework.util.extensions.showToast
import com.harismexis.breakingbad.presentation.base.BaseFragment
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.seriesName = arguments?.getString(ARG_SERIES_NAME)
    }

    override fun onCreateView() {
        setupSwipeToRefresh() { viewModel.updateEpisodes() }
        initialiseRecycler()
    }

    override fun initialiseViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = FragmentEpisodesBinding.inflate(inflater, container, false)
    }

    override fun getSwipeRefreshLayout(): SwipeRefreshLayout? {
        return binding?.swipeRefresh
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
        viewModel.updateEpisodes()
    }

    private fun observeLiveData() {
        viewModel.episodes.observe(viewLifecycleOwner, {
            when (it) {
                is EpisodesResult.Success -> populate(it.items)
                is EpisodesResult.Error -> {
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