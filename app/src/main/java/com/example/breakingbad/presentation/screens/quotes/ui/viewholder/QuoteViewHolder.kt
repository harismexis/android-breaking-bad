package com.example.breakingbad.presentation.screens.quotes.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.breakingbad.databinding.VhQuoteItemBinding
import com.example.breakingbad.domain.Quote

class QuoteViewHolder(
    private val binding: VhQuoteItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: Quote,
        position: Int
    ) {
        binding.txtQuote.text = item.quote
        binding.txtAuthor.text = item.author
        binding.txtSeries.text = item.series
    }

    fun unbind() {
        // Release resources, unsubscribe etc
    }

}