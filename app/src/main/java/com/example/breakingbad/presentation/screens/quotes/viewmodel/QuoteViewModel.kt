package com.example.breakingbad.presentation.screens.quotes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingbad.domain.Actor
import com.example.breakingbad.domain.Quote
import com.example.breakingbad.presentation.screens.home.interactors.HomeInteractors
import com.example.breakingbad.framework.extensions.getErrorMessage
import com.example.breakingbad.framework.util.functional.Action1
import com.example.breakingbad.framework.util.network.ConnectivityMonitorSimple
import com.example.breakingbad.presentation.screens.quotes.interactors.QuoteInteractors
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuoteViewModel @Inject constructor(
    private val interactors: QuoteInteractors,
    private val connectivity: ConnectivityMonitorSimple,
) : ViewModel() {

    private val TAG = QuoteViewModel::class.qualifiedName

    private val mModels = MutableLiveData<List<Quote>>()
    val models: LiveData<List<Quote>>
        get() = mModels

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
                val items = interactors.irrGetRemoteQuotes.invoke()
                mModels.value = items
                interactors.irrStoreQuotes.invoke(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

    private fun fetchLocalItems() {
        viewModelScope.launch {
            try {
                mModels.value = interactors.irrGetLocalQuotes.invoke()
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

}