package com.harismexis.breakingbad.presentation.screens.episodes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harismexis.breakingbad.core.domain.Episode
import com.harismexis.breakingbad.databinding.VhEpisodeItemBinding
import com.harismexis.breakingbad.presentation.screens.episodes.ui.viewholder.EpisodeViewHolder

class EpisodeAdapter(
    private val items: List<Episode>
) : RecyclerView.Adapter<EpisodeViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EpisodeViewHolder {
        return EpisodeViewHolder(
            VhEpisodeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        viewHolder: EpisodeViewHolder,
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

    override fun onViewDetachedFromWindow(holder: EpisodeViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

}