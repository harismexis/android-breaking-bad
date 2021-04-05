package com.harismexis.breakingbad.presentation.screens.quotes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harismexis.breakingbad.framework.extensions.getErrorMessage
import com.harismexis.breakingbad.framework.util.functional.Action1
import com.harismexis.breakingbad.framework.util.network.ConnectivityMonitorSimple
import com.harismexis.breakingbad.presentation.result.QuotesResult
import com.harismexis.breakingbad.presentation.screens.quotes.interactors.QuoteInteractors
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuotesViewModel @Inject constructor(
    private val interactors: QuoteInteractors,
    private val connectivity: ConnectivityMonitorSimple,
) : ViewModel() {

    private val TAG = QuotesViewModel::class.qualifiedName

    private val mQuotes = MutableLiveData<QuotesResult>()
    val quotes: LiveData<QuotesResult>
        get() = mQuotes

    fun bind() {
        if (connectivity.isOnline()) {
            fetchRemoteItems()
        } else {
            fetchLocalItems()
        }
    }

    fun refresh(callback: Action1<Boolean>) {
        val canRefresh = connectivity.isOnline()
        callback.call(canRefresh)
        if (canRefresh) {
            fetchRemoteItems()
        }
    }

    private fun fetchRemoteItems() {
        viewModelScope.launch {
            try {
                val items = interactors.irrGetRemoteQuotes()
                mQuotes.value = QuotesResult.QuotesSuccess(items)
                interactors.irrStoreQuotes(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mQuotes.value = QuotesResult.QuotesError(e.getErrorMessage())
            }
        }
    }

    private fun fetchLocalItems() {
        viewModelScope.launch {
            try {
                val items = interactors.irrGetLocalQuotes()
                mQuotes.value = QuotesResult.QuotesSuccess(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mQuotes.value = QuotesResult.QuotesError(e.getErrorMessage())
            }
        }
    }

}