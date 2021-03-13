package com.example.breakingbad.presentation.home.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breakingbad.databinding.FragmentHomeBinding
import com.example.breakingbad.domain.Actor
import com.example.breakingbad.framework.base.BaseFragment
import com.example.breakingbad.presentation.actordetail.ui.ActorDetailActivity.Companion.startActorDetailActivity
import com.example.breakingbad.presentation.home.ui.adapter.ActorAdapter
import com.example.breakingbad.presentation.home.ui.viewholder.ActorViewHolder
import com.example.breakingbad.presentation.home.viewmodel.HomeViewModel

class HomeFragment : BaseFragment(), ActorViewHolder.ActorClickListener,
    android.widget.SearchView.OnQueryTextListener {

    private lateinit var viewModel: HomeViewModel
    private var binding: FragmentHomeBinding? = null
    private lateinit var adapter: ActorAdapter
    private var uiModels: MutableList<Actor> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialiseViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        initialiseView()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
        viewModel.bind()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun initialiseViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[HomeViewModel::class.java]
    }

    override fun initialiseView() {
        setupSwipeToRefresh()
        initialiseRecycler()
        initialiseSearchView()
    }

    override fun onActorClick(item: Actor, position: Int) {
        requireContext().startActorDetailActivity(item.char_id)
    }

    override fun observeLiveData() {
        viewModel.models.observe(viewLifecycleOwner, {
            populate(it)
        })
    }

    override fun getToolbar(): Toolbar? {
        return null
    }

    override fun initialise() {

    }

    override fun initialiseViewBinding() {

    }

    private fun initialiseSearchView() {
        binding?.searchView?.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        viewModel.searchQuery = query
        return false
    }

    override fun onQueryTextChange(query: String?): Boolean {
        return false
    }

    private fun setupSwipeToRefresh() {
        binding?.homeSwipeRefresh?.setOnRefreshListener {
            binding?.homeSwipeRefresh?.isRefreshing = true
            viewModel.refresh { canRefresh ->
                if (! canRefresh) {
                    binding?.homeSwipeRefresh?.isRefreshing = false
                }
            }
        }
    }

    private fun initialiseRecycler() {
        adapter = ActorAdapter(uiModels, this)
        adapter.setHasStableIds(true)
        binding?.homeList?.layoutManager = LinearLayoutManager(this.context)
        binding?.homeList?.adapter = adapter
    }

    private fun populate(models: List<Actor>) {
        binding?.homeSwipeRefresh?.isRefreshing = false
        binding?.loadingProgressBar?.visibility = View.GONE
        binding?.homeList?.visibility = View.VISIBLE
        uiModels.clear()
        uiModels.addAll(models)
        adapter.notifyDataSetChanged()
    }

}