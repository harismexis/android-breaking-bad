package com.example.breakingbad.presentation.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingbad.domain.BBCharacter
import com.example.breakingbad.presentation.interactors.HomeInteractors
import com.example.breakingbad.framework.extensions.getErrorMessage
import com.example.breakingbad.framework.util.functional.Action1
import com.example.breakingbad.framework.util.network.ConnectivityMonitor
import com.example.breakingbad.framework.util.network.ConnectivityMonitorSimple
import com.example.breakingbad.framework.util.network.ConnectivityState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val interactors: HomeInteractors,
    private val connectivity: ConnectivityMonitorSimple,
) : ViewModel() {

    private val TAG = HomeViewModel::class.qualifiedName

    private var disposables: CompositeDisposable = CompositeDisposable()

    private val mModels = MutableLiveData<List<BBCharacter>>()
    val models: LiveData<List<BBCharacter>>
        get() = mModels

    var searchQuery: String? = null
        set(value) {
            field = value
            if (value.isNullOrBlank()) {
                fetchRemoteItems()
                // fetchRemoteItemsByName(null)
            } else {
                fetchRemoteItemsByName(value)
            }
        }

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
                val items = interactors.irrGetRemoteBBCharacters.invoke()
                mModels.value = items
                interactors.irrStoreBBCharacters.invoke(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

    private fun fetchRemoteItemsByName(name: String?) {
        viewModelScope.launch {
            try {
                val items = interactors.irrGetRemoteBBCharactersByName.invoke(name)
                mModels.value = items
                interactors.irrStoreBBCharacters.invoke(items)
            } catch (e: Exception) {
                 Log.d(TAG, e.getErrorMessage())
            }
        }
    }

    private fun fetchLocalItems() {
        viewModelScope.launch {
            try {
                mModels.value = interactors.irrGetLocalBBCharacters.invoke()
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

}