package com.harismexis.breakingbad.presentation.screens.home.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.harismexis.breakingbad.core.domain.Actor
import com.harismexis.breakingbad.databinding.VhActorBinding
import com.harismexis.breakingbad.framework.util.extensions.populateWithGlide

class ActorViewHolder(
    private val binding: VhActorBinding,
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
        binding.txtTitle.text = item.nickname
        binding.txtMeta.text = item.name
        itemView.setOnClickListener {
            itemClick.onActorClick(item, position)
        }
    }

    fun unbind() {
        // Release resources, unsubscribe etc
    }

}