package com.harismexis.breakingbad.presentation.screens.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.harismexis.breakingbad.core.domain.Actor
import com.harismexis.breakingbad.databinding.VhActorBinding
import com.harismexis.breakingbad.presentation.screens.home.ui.viewholder.ActorViewHolder

class ActorListAdapter(
    private val clickListener: ActorViewHolder.ActorClickListener
) : ListAdapter<Actor, ActorViewHolder>(ACTORS_COMPARATOR) {

    companion object {
        private val ACTORS_COMPARATOR = object : DiffUtil.ItemCallback<Actor>() {

            override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ActorViewHolder {
        return ActorViewHolder(
            VhActorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            clickListener
        )
    }

    override fun onBindViewHolder(
        viewHolder: ActorViewHolder,
        position: Int
    ) {
        viewHolder.bind(getItem(position), position)
    }

    override fun onViewDetachedFromWindow(holder: ActorViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

}