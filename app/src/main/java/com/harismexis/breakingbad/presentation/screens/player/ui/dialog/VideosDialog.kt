package com.harismexis.breakingbad.presentation.screens.player.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.harismexis.breakingbad.core.domain.Video
import com.harismexis.breakingbad.core.domain.videosCatalog
import com.harismexis.breakingbad.databinding.DialogVideosBinding

class VideosDialog : DialogFragment(), VideoItemViewHolder.VideoItemClickListener {

    private var binding: DialogVideosBinding? = null
    private lateinit var adapter: VideosAdapter
    private var videos: MutableList<Video> = mutableListOf()
    var itemClick: VideoItemViewHolder.VideoItemClickListener? = null

    companion object {
        private const val ARG_CURRENT_VIDEO_ID = "current_video_id"

        fun newInstance(
            selectedVideoId: String,
            itemClick: VideoItemViewHolder.VideoItemClickListener?
        ): VideosDialog {
            val fragment = VideosDialog()
            val args = Bundle()
            args.putString(ARG_CURRENT_VIDEO_ID, selectedVideoId)
            fragment.arguments = args
            fragment.itemClick = itemClick
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogVideosBinding.inflate(LayoutInflater.from(requireContext()))
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setView(binding?.root)
        setupExitIcon()
        setupRecycler()
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return builder.create()
    }

    private fun setupRecycler() {
        videos.clear()
        videos.addAll(videosCatalog)
        val selectedId = arguments?.getString(ARG_CURRENT_VIDEO_ID, videos[0].id)
        val current = videos.indexOfFirst { it.id == selectedId }
        videos[current].isPlaying = true
        adapter = VideosAdapter(videos, this)
        adapter.setHasStableIds(true)
        binding?.list?.let {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = adapter
            adapter.notifyDataSetChanged()
            it.scrollToPosition(current)
        }
    }

    private fun setupExitIcon() {
        binding?.let {
            it.iconExit.setOnClickListener { dismiss() }
        }
    }

    override fun onVideoClicked(item: Video, position: Int) {
        dismiss()
        videos.forEach { it.isPlaying = false }
        videos[position].isPlaying = true
        adapter.notifyDataSetChanged()
        itemClick?.onVideoClicked(item, position)
    }

}