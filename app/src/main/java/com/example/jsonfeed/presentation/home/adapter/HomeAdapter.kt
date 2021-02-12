package com.example.jsonfeed.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView

import com.example.jsonfeed.databinding.VhFeedItemBinding
import com.example.jsonfeed.domain.Item
import com.example.jsonfeed.presentation.home.viewholder.FeedItemVh

class HomeAdapter(
    private var models: List<Item>,
    private var clickListener: FeedItemVh.FeedItemClickListener
) : RecyclerView.Adapter<FeedItemVh>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeedItemVh {
        return FeedItemVh(
            VhFeedItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            clickListener
        )
    }

    override fun onBindViewHolder(
        viewHolder: FeedItemVh,
        position: Int
    ) {
        viewHolder.bind(models[position], position)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onViewDetachedFromWindow(holder: FeedItemVh) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

}