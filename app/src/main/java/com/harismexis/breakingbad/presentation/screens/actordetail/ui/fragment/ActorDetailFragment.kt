package com.harismexis.breakingbad.presentation.screens.actordetail.ui.fragment

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.core.domain.Actor
import com.harismexis.breakingbad.core.domain.Actor.Companion.occupationString
import com.harismexis.breakingbad.core.result.ActorDetailResult
import com.harismexis.breakingbad.databinding.ActorDetailBinding
import com.harismexis.breakingbad.databinding.FragmentActorDetailBinding
import com.harismexis.breakingbad.framework.util.extensions.populateWithGlide
import com.harismexis.breakingbad.framework.util.extensions.setTextOrUnknown
import com.harismexis.breakingbad.framework.util.extensions.showToast
import com.harismexis.breakingbad.framework.util.getLinkSpanned
import com.harismexis.breakingbad.presentation.base.BaseDIFragment
import com.harismexis.breakingbad.presentation.screens.actordetail.viewmodel.ActorDetailViewModel

class ActorDetailFragment : BaseDIFragment() {

    private var binding: FragmentActorDetailBinding? = null
    private var detailBinding: ActorDetailBinding? = null
    private val viewModel: ActorDetailViewModel by viewModels { viewModelFactory }

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
        fetchActorDetails()
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

    override fun initialiseViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = FragmentActorDetailBinding.inflate(inflater, container, false)
        detailBinding = binding?.itemDetail
    }

    override fun getRootView() = binding?.root

    private fun observeLiveData() {
        viewModel.actorDetailResult.observe(viewLifecycleOwner, {
            when (it) {
                is ActorDetailResult.Success -> populate(it.item)
                is ActorDetailResult.Error -> populateError(it.error)
            }
        })
    }

    private fun populateError(error: String) {
        requireContext().showToast(error)
    }

    override fun onCreateView() {}

    private fun fetchActorDetails() {
        val actorId = arguments?.getInt(ARG_ACTOR_ID)
        actorId?.let {
            viewModel.retrieveActorById(it)
        }
    }

    private fun populate(actor: Actor) {
        binding?.let {
            it.toolbarTitle.text = actor.nickname
            it.toolbar.setNavigationIcon(R.drawable.ic_arrow_left_white_rounded_24dp)
        }
        detailBinding?.let {
            requireContext().populateWithGlide(it.img, actor.img)
            it.txtName.setTextOrUnknown(actor.name)
            it.txtPortrayed.setTextOrUnknown(actor.portrayed)
            it.txtOccupation.setTextOrUnknown(actor.occupationString())
            it.txtBirthday.setTextOrUnknown(actor.birthday)
            it.txtStatus.setTextOrUnknown(actor.status)
            it.txtCategory.setTextOrUnknown(actor.category.toString())
            it.txtImgUrl.movementMethod = LinkMovementMethod.getInstance()
            it.txtImgUrl.text = getLinkSpanned(
                getString(R.string.missing_img_url),
                getString(R.string.character_image),
                actor.img
            )
        }
    }

    override fun getSwipeRefreshLayout(): SwipeRefreshLayout? = null

}