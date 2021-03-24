package com.harismexis.breakingbad.presentation.screens.player

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.harismexis.breakingbad.framework.base.BaseFragment
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.databinding.FragmentPlayerBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class PlayerFragment : BaseFragment() {

    private var binding: FragmentPlayerBinding? = null
    private var youTubePlayer: YouTubePlayer? = null

    override fun initialiseView() {
        setupToolbar()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onViewCreated() {
        loadVideo("QmHCn5xXHjI")
    }

    override fun initialiseViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentPlayerBinding.inflate(inflater, container, false)
    }

    override fun getRootView(): View? {
        return binding?.root
    }

    private fun loadVideo(videoId: String) {
        binding?.let {
            lifecycle.addObserver(it.youTubeView)
            it.youTubeView.initialize(object : AbstractYouTubePlayerListener() {
                override fun onReady(@NonNull player: YouTubePlayer) {
                    youTubePlayer = player
                    youTubePlayer?.loadVideo(videoId, 0f)
                }
            })
        }
    }

    private fun setupToolbar() {
        val navController = findNavController()
        val appBarConf = AppBarConfiguration(navController.graph)
        binding?.let {
            it.toolbar.setupWithNavController(navController, appBarConf)
            it.toolbar.setNavigationIcon(R.drawable.ic_arrow_left_white_rounded_24dp)
        }
    }

    override fun initialiseViewModel() {}

    override fun observeLiveData() {}

}