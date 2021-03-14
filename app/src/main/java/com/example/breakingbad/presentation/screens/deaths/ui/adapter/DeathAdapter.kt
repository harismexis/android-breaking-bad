package com.example.breakingbad.presentation.screens.deaths.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.breakingbad.databinding.VhDeathItemBinding
import com.example.breakingbad.domain.Death
import com.example.breakingbad.presentation.screens.deaths.ui.viewholder.DeathViewHolder

class DeathAdapter(
    private val models: List<Death>
) : RecyclerView.Adapter<DeathViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DeathViewHolder {
        return DeathViewHolder(
            VhDeathItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        viewHolder: DeathViewHolder,
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

    override fun onViewDetachedFromWindow(holder: DeathViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

}