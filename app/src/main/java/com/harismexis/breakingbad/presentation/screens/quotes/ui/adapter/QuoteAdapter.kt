package com.harismexis.breakingbad.presentation.screens.quotes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harismexis.breakingbad.databinding.VhQuoteItemBinding
import com.harismexis.breakingbad.model.domain.Quote
import com.harismexis.breakingbad.presentation.screens.quotes.ui.viewholder.QuoteViewHolder

class QuoteAdapter(
    private val models: List<Quote>
) : RecyclerView.Adapter<QuoteViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuoteViewHolder {
        return QuoteViewHolder(
            VhQuoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        viewHolder: QuoteViewHolder,
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

    override fun onViewDetachedFromWindow(holder: QuoteViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

}