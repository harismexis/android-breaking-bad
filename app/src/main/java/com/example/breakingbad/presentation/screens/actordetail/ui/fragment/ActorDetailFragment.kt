package com.example.breakingbad.presentation.screens.actordetail.ui.fragment

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.breakingbad.R
import com.example.breakingbad.databinding.ActorDetailViewBinding
import com.example.breakingbad.databinding.FragmentActorDetailBinding
import com.example.breakingbad.domain.Actor
import com.example.breakingbad.framework.base.BaseFragment
import com.example.breakingbad.framework.extensions.getLinkSpanned
import com.example.breakingbad.framework.extensions.populateWithGlide
import com.example.breakingbad.framework.extensions.setTextOrUnknown
import com.example.breakingbad.presentation.screens.actordetail.viewmodel.ActorDetailViewModel

class ActorDetailFragment : BaseFragment() {

    private var binding: FragmentActorDetailBinding? = null
    private var detailBinding: ActorDetailViewBinding? = null
    private lateinit var viewModel: ActorDetailViewModel

    companion object {
        private const val ARG_ACTOR_ID = "actorId"

        fun newInstance(actorId: Int): ActorDetailFragment {
            val args = Bundle()
            args.putInt(ARG_ACTOR_ID, actorId)
            val fragment = ActorDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated() {
        setupToolbar()
        observeLiveData()
        fetchLocalItem()
    }

    private fun setupToolbar() {
        val navController = findNavController()
        val appBarConf = AppBarConfiguration(navController.graph)
        binding?.toolbar?.setupWithNavController(navController, appBarConf)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun initialiseViewModel() {
        viewModel = ViewModelProviders
            .of(this, viewModelFactory)[ActorDetailViewModel::class.java]
    }

    override fun initialiseViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = FragmentActorDetailBinding.inflate(inflater, container, false)
        detailBinding = binding?.itemDetailContainer
    }

    override fun getRootView() = binding?.root

    override fun observeLiveData() {
        viewModel.model.observe(viewLifecycleOwner, {
            populate(it)
        })
    }

    override fun initialiseView() {}

    private fun fetchLocalItem() {
        val actorId = arguments?.getInt(ARG_ACTOR_ID)
        actorId?.let {
            viewModel.retrieveItemById(it)
        }
    }

    private fun populate(item: Actor) {
        binding?.let {
            it.toolbarTitle.text = item.name
            it.toolbar.setNavigationIcon(R.drawable.ic_arrow_left_white_rounded_24dp)
        }
        detailBinding?.let {
            requireContext().populateWithGlide(it.img, item.img)
            it.txtName.setTextOrUnknown(item.name)
            it.txtBirthday.setTextOrUnknown(item.birthday)
            it.txtStatus.setTextOrUnknown(item.status)
            it.txtNickname.setTextOrUnknown(item.nickname)
            it.txtPortrayed.setTextOrUnknown(item.portrayed)
            it.txtCategory.setTextOrUnknown(item.category.toString())
            it.txtImgUrl.movementMethod = LinkMovementMethod.getInstance()
            it.txtImgUrl.text = getLinkSpanned(
                getString(R.string.missing_img_url),
                getString(R.string.character_image),
                item.img
            )
        }
    }

}