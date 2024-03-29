package com.harismexis.breakingbad.presentation.screens.quotes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harismexis.breakingbad.core.domain.Quote
import com.harismexis.breakingbad.databinding.VhQuoteBinding
import com.harismexis.breakingbad.presentation.screens.quotes.ui.viewholder.QuoteViewHolder

class QuoteAdapter(
    private val items: List<Quote>
) : RecyclerView.Adapter<QuoteViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuoteViewHolder {
        return QuoteViewHolder(
            VhQuoteBinding.inflate(
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
        viewHolder.bind(items[position], position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onViewDetachedFromWindow(holder: QuoteViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

}