package com.harismexis.breakingbad.presentation.screens.player.videosdialog

import androidx.recyclerview.widget.RecyclerView
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.databinding.VhVideoItemBinding
import com.harismexis.breakingbad.framework.extensions.getColorCompat

class VideoItemViewHolder(
    private val binding: VhVideoItemBinding,
    private val itemClick: VideoItemClickListener?
) : RecyclerView.ViewHolder(binding.root) {

    interface VideoItemClickListener {
        fun onVideoClicked(item: VideoItem, position: Int)
    }

    fun bind(
        item: VideoItem,
        position: Int
    ) {
        binding.txtInfo.text = item.videoTitle
        val color = if (item.isPlaying) itemView.context.getColorCompat(R.color.light_green_six)
                    else itemView.context.getColorCompat(R.color.white)
        binding.txtInfo.setTextColor(color)
        itemView.setOnClickListener { itemClick?.onVideoClicked(item, position) }
    }

}