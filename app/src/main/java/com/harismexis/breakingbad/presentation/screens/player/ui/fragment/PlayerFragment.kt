package com.harismexis.breakingbad.presentation.screens.player.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.annotation.NonNull
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.core.domain.Video
import com.harismexis.breakingbad.core.domain.breakingBadBlooperReel
import com.harismexis.breakingbad.databinding.FragmentPlayerBinding
import com.harismexis.breakingbad.framework.util.extensions.hideSystemUI
import com.harismexis.breakingbad.framework.util.extensions.showSystemUI
import com.harismexis.breakingbad.presentation.base.BaseFragment
import com.harismexis.breakingbad.presentation.screens.player.ui.dialog.VideoViewHolder
import com.harismexis.breakingbad.presentation.screens.player.ui.dialog.VideoPickerDialog
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.menu.MenuItem

class PlayerFragment : BaseFragment(), VideoViewHolder.VideoItemClickListener {

    private lateinit var videoId: String
    private var binding: FragmentPlayerBinding? = null
    private var videoPlayer: YouTubePlayer? = null
    private var isFullScreen: Boolean = false

    companion object {
        const val TAG_VIDEOS_DIALOG = "videos_dialog"
        const val ARG_VIDEO_ID = "arg_video_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            videoId = it.getString(ARG_VIDEO_ID, breakingBadBlooperReel)
        }
    }

    override fun initialiseViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentPlayerBinding.inflate(inflater, container, false)
    }

    override fun onCreateView() {
        addBackNavigation()
        initFullScreenBtn()
        initPlayerMenu()
    }

    override fun onViewCreated() {
        initPlayerAndStartPlayback(videoId)
    }

    private fun initPlayerAndStartPlayback(videoId: String) {
        binding?.let {
            lifecycle.addObserver(it.youTubeView)
            it.youTubeView.initialize(object : AbstractYouTubePlayerListener() {
                override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                    videoPlayer = youTubePlayer
                    videoPlayer?.loadVideo(videoId, 0f)
                }
            })
        }
    }

    private fun addBackNavigation() {
        binding?.let {
            val controls = it.youTubeView.findViewById<RelativeLayout>(R.id.controls_container)
            val backIcon = ImageView(context)
            val params = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT,
            )
            params.marginStart =
                resources.getDimensionPixelSize(R.dimen.player_back_icon_margin_start)
            params.topMargin = resources.getDimensionPixelSize(R.dimen.player_back_icon_margin_top)
            params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE)
            params.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE)
            backIcon.layoutParams = params
            backIcon.setImageResource(R.drawable.ic_arrow_left_white_rounded_24dp)
            backIcon.setOnClickListener {
                // TODO: Here it navigates always to Home
                findNavController().popBackStack()
            }
            controls.addView(backIcon)
        }
    }

    private fun initPlayerMenu() {
        binding?.let {
            val controller = it.youTubeView.getPlayerUiController()
            controller.showMenuButton(true)
            controller.getMenu()?.addItem(MenuItem(getString(R.string.videos))
            { showVideosDialog() })
        }
    }

    private fun showVideosDialog() {
        binding?.youTubeView?.getPlayerUiController()?.getMenu()?.dismiss()
        VideoPickerDialog.newInstance(videoId, this)
            .show(childFragmentManager, TAG_VIDEOS_DIALOG)
    }

    private fun initFullScreenBtn() {
        binding?.let {
            it.youTubeView.getPlayerUiController()
                .setFullScreenButtonClickListener {
                    isFullScreen = !isFullScreen
                    if (isFullScreen) requireActivity().hideSystemUI()
                    else requireActivity().showSystemUI()
                }
        }
    }

    override fun onDestroyView() {
        if (isFullScreen) requireActivity().showSystemUI()
        binding = null
        super.onDestroyView()
    }

    override fun getRootView(): View? {
        return binding?.root
    }

    override fun onVideoClicked(item: Video, position: Int) {
        videoId = item.id
        videoPlayer?.loadVideo(item.id, 0f)
    }

    override fun getSwipeRefreshLayout(): SwipeRefreshLayout? = null

}