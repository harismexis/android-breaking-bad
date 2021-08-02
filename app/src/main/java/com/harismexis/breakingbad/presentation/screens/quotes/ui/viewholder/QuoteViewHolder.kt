package com.harismexis.breakingbad.presentation.screens.quotes.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.harismexis.breakingbad.core.domain.Quote
import com.harismexis.breakingbad.databinding.VhQuoteItemBinding

class QuoteViewHolder(
    private val binding: VhQuoteItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: Quote,
        position: Int
    ) {
        binding.txtQuote.text = item.quote?.trim('.')
        binding.txtAuthor.text = item.author
    }

    fun unbind() {
        // Release resources, unsubscribe etc
    }

}