package com.harismexis.breakingbad.presentation.screens.player

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.annotation.NonNull
import androidx.navigation.fragment.findNavController
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.databinding.FragmentPlayerLinearBinding
import com.harismexis.breakingbad.framework.base.BaseFragment
import com.harismexis.breakingbad.framework.util.ui.hideSystemUI
import com.harismexis.breakingbad.framework.util.ui.showSystemUI
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.menu.MenuItem

class PlayerFragment : BaseFragment() {

    private val videos = provideVideos()
    private var binding: FragmentPlayerLinearBinding? = null
    private var videoPlayer: YouTubePlayer? = null
    private var isFullScreen: Boolean = false

    override fun initialiseViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentPlayerLinearBinding.inflate(inflater, container, false)
    }

    override fun onCreateView() {
        addBackNavigation()
        initFullScreen()
        initPlayerMenu()
    }

    override fun onViewCreated() {
        loadVideo(videos[0].videoId)
    }

    private fun loadVideo(videoId: String) {
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
                findNavController().navigate(PlayerFragmentDirections.actionExit())
            }
            controls.addView(backIcon)
        }
    }

    private fun initPlayerMenu() {
        binding?.let {
            val controller = it.youTubeView.getPlayerUiController()
            controller.showMenuButton(true)
            val menu = controller.getMenu()
            for (i in 0 until videos.size) {
                val id = videos[i].videoId
                val title = videos[i].videoTitle
                menu?.addItem(MenuItem(
                    title,
                    R.drawable.ic_play_circle_black_24dp
                )
                { videoPlayer?.loadVideo(id, 0f) })
            }
        }
    }

    private fun initFullScreen() {
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

}