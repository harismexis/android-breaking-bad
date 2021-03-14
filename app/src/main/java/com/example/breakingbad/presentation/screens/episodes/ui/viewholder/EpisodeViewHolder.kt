package com.example.breakingbad.presentation.screens.episodes.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.breakingbad.databinding.VhEpisodeItemBinding
import com.example.breakingbad.domain.Episode

class EpisodeViewHolder(
    private val binding: VhEpisodeItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: Episode,
        position: Int
    ) {
        binding.txtTitle.text = item.title
        binding.txtSeason.text = item.season
        binding.txtAirDate.text = item.air_date
        binding.txtActors.text = item.characters?.joinToString(",")
        binding.txtSeries.text = item.series
    }

    fun unbind() {
        // Release resources, unsubscribe etc
    }

}