package com.example.breakingbad.presentation.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.breakingbad.databinding.VhBbactorItemBinding
import com.example.breakingbad.domain.BBActor
import com.example.breakingbad.presentation.home.ui.viewholder.BBActorViewHolder

class BBActorAdapter(
    private val models: List<BBActor>,
    private val clickListener: BBActorViewHolder.BBCharacterClickListener
) : RecyclerView.Adapter<BBActorViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BBActorViewHolder {
        return BBActorViewHolder(
            VhBbactorItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            clickListener
        )
    }

    override fun onBindViewHolder(
        viewHolder: BBActorViewHolder,
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

    override fun onViewDetachedFromWindow(holder: BBActorViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

}