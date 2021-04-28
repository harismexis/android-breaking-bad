package com.harismexis.breakingbad.presentation.screens.player

import androidx.recyclerview.widget.RecyclerView
import com.harismexis.breakingbad.databinding.VhVideoItemBinding

class VideoItemViewHolder(
    private val binding: VhVideoItemBinding,
    private val itemClick: VideoItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    interface VideoItemClickListener {
        fun onVideoClicked(item: VideoItem, position: Int)
    }

    fun bind(
        item: VideoItem,
        position: Int
    ) {
        binding.txtInfo.text = item.videoTitle
        itemView.setOnClickListener { itemClick.onVideoClicked(item, position) }
    }

    fun unbind() {
        // Release resources, unsubscribe etc
    }

}