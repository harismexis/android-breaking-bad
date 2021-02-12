package com.example.jsonfeed.presentation.home.viewholder

import androidx.recyclerview.widget.RecyclerView

import com.example.jsonfeed.databinding.VhFeedItemBinding
import com.example.jsonfeed.domain.Item
import com.example.jsonfeed.framework.extensions.populateWithGlide

class FeedItemVh(
    private var binding: VhFeedItemBinding,
    private var itemClick: FeedItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    interface FeedItemClickListener {
        fun onFeedItemClick(item: Item, position: Int)
    }

    fun bind(
        item: Item,
        position: Int
    ) {
        itemView.context.populateWithGlide(binding.imgContainer, item.imageUrl)
        binding.txtName.text = item.name
        binding.txtMeta.text = item.supertype
        itemView.setOnClickListener {
            itemClick.onFeedItemClick(item, position)
        }
    }

    fun unbind() {
        // Release resources, unsubscribe etc
    }

}