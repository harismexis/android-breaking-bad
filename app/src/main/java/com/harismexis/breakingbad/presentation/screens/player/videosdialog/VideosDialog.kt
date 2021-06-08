package com.harismexis.breakingbad.presentation.screens.player.videosdialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.harismexis.breakingbad.databinding.DialogVideosBinding
import com.harismexis.breakingbad.presentation.screens.player.VideosCatalog

class VideosDialog : DialogFragment(), VideoItemViewHolder.VideoItemClickListener {

    private var binding: DialogVideosBinding? = null
    private lateinit var adapter: VideosAdapter
    private var videos: MutableList<VideoItem> = mutableListOf()
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
        setupCloseIcon()
        setupRecycler()
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return builder.create()
    }

    private fun setupRecycler() {
        videos.clear()
        videos.addAll(VideosCatalog.getVideoItems())

        val selectedId = arguments?.getString(ARG_CURRENT_VIDEO_ID, videos[0].videoId)
        val current = videos.indexOfFirst { it.videoId == selectedId }
        videos[current].isPlaying = true

        adapter = VideosAdapter(videos, this)
        adapter.setHasStableIds(true)
        binding?.list?.let {
            it.layoutManager = LinearLayoutManager(this.context)
            it.adapter = adapter
            adapter.notifyDataSetChanged()
            it.scrollToPosition(current)
        }
    }

    private fun setupCloseIcon() {
        binding?.let {
            it.iconExit.setOnClickListener { dismiss() }
        }
    }

    override fun onVideoClicked(item: VideoItem, position: Int) {
        dismiss()
        videos.forEach { it.isPlaying = false }
        videos[position].isPlaying = true
        adapter.notifyDataSetChanged()
        itemClick?.onVideoClicked(item, position)
    }

}