package com.harismexis.breakingbad.presentation.screens.player.ui.dialog

import androidx.recyclerview.widget.RecyclerView
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.core.domain.Video
import com.harismexis.breakingbad.databinding.VhVideoBinding
import com.harismexis.breakingbad.framework.util.extensions.getColorCompat

class VideoViewHolder(
    private val binding: VhVideoBinding,
    private val itemClick: VideoItemClickListener?
) : RecyclerView.ViewHolder(binding.root) {

    interface VideoItemClickListener {
        fun onVideoSelected(item: Video, position: Int)
    }

    fun bind(
        item: Video,
        position: Int
    ) {
        binding.txtInfo.text = item.title
        val color = if (item.isPlaying) itemView.context.getColorCompat(R.color.main_color)
                    else itemView.context.getColorCompat(R.color.white)
        binding.txtInfo.setTextColor(color)
        itemView.setOnClickListener { itemClick?.onVideoSelected(item, position) }
    }

}