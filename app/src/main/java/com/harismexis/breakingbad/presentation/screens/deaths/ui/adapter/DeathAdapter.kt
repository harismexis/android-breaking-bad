package com.harismexis.breakingbad.presentation.screens.deaths.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harismexis.breakingbad.core.domain.Death
import com.harismexis.breakingbad.databinding.VhDeathItemBinding
import com.harismexis.breakingbad.presentation.screens.deaths.ui.viewholder.DeathViewHolder

class DeathAdapter(
    private val items: List<Death>
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
        viewHolder.bind(items[position], position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onViewDetachedFromWindow(holder: DeathViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

}