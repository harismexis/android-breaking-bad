package com.example.jsonfeed.mockproviders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jsonfeed.domain.Item
import com.example.jsonfeed.presentation.detail.viewmodel.ItemDetailVm

import io.mockk.mockk

object MockItemDetailVmProvider {

    private var mockItemDetailVm: ItemDetailVm = mockk(relaxed = true)

    var mModel = MutableLiveData<Item>()
    val model: LiveData<Item>
        get() = mModel

    fun provideMockItemDetailVm(): ItemDetailVm {
        return mockItemDetailVm
    }

}