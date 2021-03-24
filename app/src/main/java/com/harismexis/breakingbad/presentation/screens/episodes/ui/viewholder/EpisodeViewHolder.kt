package com.harismexis.breakingbad.presentation.screens.episodes.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.harismexis.breakingbad.domain.Episode
import com.harismexis.breakingbad.databinding.VhEpisodeItemBinding

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