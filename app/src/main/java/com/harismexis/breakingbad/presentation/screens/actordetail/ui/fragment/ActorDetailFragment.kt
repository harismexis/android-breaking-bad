package com.harismexis.breakingbad.presentation.screens.actordetail.ui.fragment

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.databinding.ActorDetailViewBinding
import com.harismexis.breakingbad.databinding.FragmentActorDetailBinding
import com.harismexis.breakingbad.domain.Actor
import com.harismexis.breakingbad.presentation.base.BaseFragment
import com.harismexis.breakingbad.framework.extensions.getLinkSpanned
import com.harismexis.breakingbad.framework.extensions.populateWithGlide
import com.harismexis.breakingbad.framework.extensions.setTextOrUnknown
import com.harismexis.breakingbad.framework.extensions.showToast
import com.harismexis.breakingbad.presentation.result.ActorDetailResult
import com.harismexis.breakingbad.presentation.screens.actordetail.viewmodel.ActorDetailViewModel

class ActorDetailFragment : BaseFragment() {

    private var binding: FragmentActorDetailBinding? = null
    private var detailBinding: ActorDetailViewBinding? = null
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

    override fun initialiseViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = FragmentActorDetailBinding.inflate(inflater, container, false)
        detailBinding = binding?.itemDetailContainer
    }

    override fun getRootView() = binding?.root

    private fun observeLiveData() {
        viewModel.model.observe(viewLifecycleOwner, {
            when (it) {
                is ActorDetailResult.ActorSuccess -> populate(it.item)
                is ActorDetailResult.ActorError -> populateError(it.error)
            }
        })
    }

    private fun populateError(error: String) {
        requireContext().showToast(error)
    }

    override fun onCreateView() {}

    private fun fetchLocalItem() {
        val actorId = arguments?.getInt(ARG_ACTOR_ID)
        actorId?.let {
            viewModel.retrieveActorById(it)
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