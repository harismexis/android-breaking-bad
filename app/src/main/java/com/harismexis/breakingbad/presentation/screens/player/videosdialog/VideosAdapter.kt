package com.harismexis.breakingbad.presentation.screens.player.videosdialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harismexis.breakingbad.databinding.VhVideoItemBinding

class VideosAdapter(
    private val items: List<Video>,
    private val itemClick: VideoItemViewHolder.VideoItemClickListener?
) : RecyclerView.Adapter<VideoItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideoItemViewHolder {
        return VideoItemViewHolder(
            VhVideoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            itemClick
        )
    }

    override fun onBindViewHolder(
        viewHolder: VideoItemViewHolder,
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