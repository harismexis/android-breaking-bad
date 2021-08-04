package com.harismexis.breakingbad.presentation.screens.deaths.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.harismexis.breakingbad.core.domain.Death
import com.harismexis.breakingbad.databinding.VhDeathBinding

class DeathViewHolder(
    private val binding: VhDeathBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: Death,
        position: Int
    ) {
        binding.txtDeath.text = item.death?.trim('.')
        binding.txtCause.text = item.cause?.trim('.')
        binding.txtResponsible.text = item.responsible
    }

    fun unbind() {
        // Release resources
    }

}