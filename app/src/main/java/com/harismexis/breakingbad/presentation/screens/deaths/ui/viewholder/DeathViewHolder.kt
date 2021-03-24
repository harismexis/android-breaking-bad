package com.harismexis.breakingbad.presentation.screens.deaths.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.harismexis.breakingbad.domain.Death
import com.harismexis.breakingbad.databinding.VhDeathItemBinding

class DeathViewHolder(
    private val binding: VhDeathItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: Death,
        position: Int
    ) {
        binding.txtDeath.text = item.death
        binding.txtCause.text = item.cause
        binding.txtResponsible.text = item.responsible
    }

    fun unbind() {
        // Release resources, unsubscribe etc
    }

}