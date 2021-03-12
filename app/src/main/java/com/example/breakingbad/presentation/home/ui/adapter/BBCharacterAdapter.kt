package com.example.breakingbad.presentation.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.breakingbad.databinding.VhBbCharacterItemBinding
import com.example.breakingbad.domain.BBCharacter
import com.example.breakingbad.presentation.home.ui.viewholder.BBCharacterViewHolder

class BBCharacterAdapter(
    private val models: List<BBCharacter>,
    private val clickListener: BBCharacterViewHolder.BBCharacterClickListener
) : RecyclerView.Adapter<BBCharacterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BBCharacterViewHolder {
        return BBCharacterViewHolder(
            VhBbCharacterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            clickListener
        )
    }

    override fun onBindViewHolder(
        viewHolder: BBCharacterViewHolder,
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

    override fun onViewDetachedFromWindow(holder: BBCharacterViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

}