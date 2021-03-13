package com.example.breakingbad.presentation.screens.home.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.breakingbad.databinding.VhActorItemBinding
import com.example.breakingbad.domain.Actor
import com.example.breakingbad.framework.extensions.populateWithGlide

class ActorViewHolder(
    private val binding: VhActorItemBinding,
    private val itemClick: ActorClickListener
) : RecyclerView.ViewHolder(binding.root) {

    interface ActorClickListener {
        fun onActorClick(item: Actor, position: Int)
    }

    fun bind(
        item: Actor,
        position: Int
    ) {
        itemView.context.populateWithGlide(binding.imgView, item.img)
        binding.txtName.text = item.name
        binding.txtMeta.text = item.nickname
        itemView.setOnClickListener {
            itemClick.onActorClick(item, position)
        }
    }

    fun unbind() {
        // Release resources, unsubscribe etc
    }

}