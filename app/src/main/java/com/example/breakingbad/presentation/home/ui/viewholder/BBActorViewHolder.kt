package com.example.breakingbad.presentation.home.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.breakingbad.databinding.VhBbactorItemBinding
import com.example.breakingbad.domain.BBActor
import com.example.breakingbad.framework.extensions.populateWithGlide

class BBActorViewHolder(
    private val binding: VhBbactorItemBinding,
    private val itemClick: BBCharacterClickListener
) : RecyclerView.ViewHolder(binding.root) {

    interface BBCharacterClickListener {
        fun onBBCharacterClick(item: BBActor, position: Int)
    }

    fun bind(
        item: BBActor,
        position: Int
    ) {
        itemView.context.populateWithGlide(binding.imgView, item.img)
        binding.txtName.text = item.name
        binding.txtMeta.text = item.nickname
        itemView.setOnClickListener {
            itemClick.onBBCharacterClick(item, position)
        }
    }

    fun unbind() {
        // Release resources, unsubscribe etc
    }

}