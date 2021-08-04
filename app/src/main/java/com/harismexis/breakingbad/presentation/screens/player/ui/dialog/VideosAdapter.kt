package com.harismexis.breakingbad.presentation.screens.player.ui.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harismexis.breakingbad.core.domain.Video
import com.harismexis.breakingbad.databinding.VhVideoBinding

class VideosAdapter(
    private val items: List<Video>,
    private val itemClick: VideoViewHolder.VideoItemClickListener?
) : RecyclerView.Adapter<VideoViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideoViewHolder {
        return VideoViewHolder(
            VhVideoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            itemClick
        )
    }

    override fun onBindViewHolder(
        viewHolder: VideoViewHolder,
        position: Int
    ) {
        viewHolder.bind(items[position], position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}