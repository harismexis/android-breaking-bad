package com.example.breakingbad.presentation.screens.deaths.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.breakingbad.databinding.VhDeathItemBinding
import com.example.breakingbad.domain.Death

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