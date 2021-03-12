package com.example.breakingbad.setup.mockvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.breakingbad.domain.BBCharacter
import com.example.breakingbad.presentation.bbcharacterdetail.viewmodel.BBCharacterDetailViewModel

import io.mockk.mockk

object MockBBCharacterDetailVmProvider {

    private var mockBBCharacterDetailVm: BBCharacterDetailViewModel = mockk(relaxed = true)

    var mModel = MutableLiveData<BBCharacter>()
    val model: LiveData<BBCharacter>
        get() = mModel

    fun provideMockBBCharacterDetailVm(): BBCharacterDetailViewModel {
        return mockBBCharacterDetailVm
    }

}