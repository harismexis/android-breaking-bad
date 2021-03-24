package com.harismexis.breakingbad.presentation.screens.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harismexis.breakingbad.domain.Actor
import com.harismexis.breakingbad.presentation.screens.home.ui.viewholder.ActorViewHolder
import com.harismexis.breakingbad.databinding.VhActorItemBinding

class ActorAdapter(
    private val models: List<Actor>,
    private val clickListener: ActorViewHolder.ActorClickListener
) : RecyclerView.Adapter<ActorViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ActorViewHolder {
        return ActorViewHolder(
            VhActorItemBinding.inflate(
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
        viewHolder.bind(models[position], position)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onViewDetachedFromWindow(holder: ActorViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

}