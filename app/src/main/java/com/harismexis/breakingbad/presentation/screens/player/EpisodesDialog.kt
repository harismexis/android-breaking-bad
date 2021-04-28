package com.harismexis.breakingbad.presentation.screens.player

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.harismexis.breakingbad.databinding.DialogEpisodesBinding

class EpisodesDialog : DialogFragment(), VideoItemViewHolder.VideoItemClickListener {

    private var binding: DialogEpisodesBinding? = null
    private lateinit var adapter: VideosAdapter
    private var uiModels: MutableList<VideoItem> = mutableListOf()
    var itemClick: VideoItemViewHolder.VideoItemClickListener? = null

    private fun setupRecycler() {
        uiModels.clear()
        uiModels.addAll(provideVideos())
        adapter = VideosAdapter(uiModels, this)
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
    
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogEpisodesBinding.inflate(LayoutInflater.from(requireActivity()))
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding?.root)
        setupCloseIcon()
        setupRecycler()
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return builder.create()
    }

    companion object {

        fun newInstance(
            itemClick: VideoItemViewHolder.VideoItemClickListener?
        ): EpisodesDialog {
            val frag = EpisodesDialog()
            frag.itemClick = itemClick
            return frag
        }
    }

    override fun onVideoClicked(item: VideoItem, position: Int) {
        dismiss()
        itemClick?.onVideoClicked(item, position)
    }

}