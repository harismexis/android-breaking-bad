package com.harismexis.breakingbad.presentation.screens.player

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.harismexis.breakingbad.databinding.DialogVideosBinding

class VideosDialog : DialogFragment(), VideoItemViewHolder.VideoItemClickListener {

    private var binding: DialogVideosBinding? = null
    private lateinit var adapter: VideosAdapter
    private var videos: MutableList<VideoItem> = mutableListOf()
    var itemClick: VideoItemViewHolder.VideoItemClickListener? = null

    companion object {

        fun newInstance(
            itemClick: VideoItemViewHolder.VideoItemClickListener?
        ): VideosDialog {
            val frag = VideosDialog()
            frag.itemClick = itemClick
            return frag
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
        videos.addAll(provideVideos())
        adapter = VideosAdapter(videos, this)
        adapter.setHasStableIds(true)
        binding?.list?.let {
            it.layoutManager = LinearLayoutManager(this.context)
            it.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }

    private fun setupCloseIcon() {
        binding?.let {
            it.iconExit.setOnClickListener { dismiss() }
        }
    }

    override fun onVideoClicked(item: VideoItem, position: Int) {
        dismiss()
        itemClick?.onVideoClicked(item, position)
    }

}