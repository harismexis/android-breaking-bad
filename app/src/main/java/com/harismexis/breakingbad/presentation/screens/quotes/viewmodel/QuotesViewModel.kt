package com.harismexis.breakingbad.presentation.screens.quotes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.breakingbad.core.repository.quote.QuotesLocal
import com.harismexis.breakingbad.core.repository.quote.QuotesRemote
import com.harismexis.breakingbad.core.result.QuotesResult
import com.harismexis.breakingbad.framework.util.event.Event
import com.harismexis.breakingbad.framework.util.extensions.getErrorMessage
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuotesViewModel @Inject constructor(
    private val quoteRemote: QuotesRemote,
    private val quoteLocal: QuotesLocal
) : ViewModel() {

    private val TAG = QuotesViewModel::class.qualifiedName

    private val mQuotes = MutableLiveData<QuotesResult>()
    val quotes: LiveData<QuotesResult>
        get() = mQuotes

    private val mShowErrorMessage = MutableLiveData<Event<String>>()
    val showErrorMessage: LiveData<Event<String>>
        get() = mShowErrorMessage

    var seriesName: String? = null

    fun updateQuotes() {
        fetchQuotes(seriesName)
    }

    private fun fetchQuotes(seriesName: String?) {
        viewModelScope.launch {
            try {
                val items = quoteRemote.getQuotes(seriesName)
                mQuotes.value = QuotesResult.Success(items)
                quoteLocal.insertQuotes(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mShowErrorMessage.value = Event(e.getErrorMessage())
                fetchCachedQuotes(seriesName)
            }
        }
    }

    private suspend fun fetchCachedQuotes(seriesName: String?) {
        try {
            val items = quoteLocal.getQuotesBySeries(seriesName)
            mQuotes.value = QuotesResult.Success(items)
        } catch (e: Exception) {
            Log.d(TAG, e.getErrorMessage())
            mQuotes.value = QuotesResult.Error(e)
            mShowErrorMessage.value = Event(e.getErrorMessage())
        }

    }

}