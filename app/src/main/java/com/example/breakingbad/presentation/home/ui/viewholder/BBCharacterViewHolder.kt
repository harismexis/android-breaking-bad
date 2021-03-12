package com.example.breakingbad.presentation.home.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.breakingbad.databinding.VhBbCharacterItemBinding
import com.example.breakingbad.domain.BBCharacter
import com.example.breakingbad.framework.extensions.populateWithGlide

class BBCharacterViewHolder(
    private val binding: VhBbCharacterItemBinding,
    private val itemClick: BBCharacterClickListener
) : RecyclerView.ViewHolder(binding.root) {

    interface BBCharacterClickListener {
        fun onBBCharacterClick(item: BBCharacter, position: Int)
    }

    fun bind(
        item: BBCharacter,
        position: Int
    ) {
        itemView.context.populateWithGlide(binding.imgView, item.imageUrl)
        binding.txtName.text = item.name
        binding.txtMeta.text = item.origin
        itemView.setOnClickListener {
            itemClick.onBBCharacterClick(item, position)
        }
    }

    fun unbind() {
        // Release resources, unsubscribe etc
    }

}