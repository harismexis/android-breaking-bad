package com.harismexis.breakingbad.presentation.screens.home.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.harismexis.breakingbad.framework.util.extensions.populateWithGlide
import com.harismexis.breakingbad.databinding.VhActorItemBinding
import com.harismexis.breakingbad.model.domain.Actor

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