package com.harismexis.breakingbad.setup.mockvm

import androidx.lifecycle.ViewModel
import com.harismexis.breakingbad.presentation.screens.actordetail.viewmodel.ActorDetailViewModel
import com.harismexis.breakingbad.presentation.screens.home.viewmodel.HomeViewModel

import javax.inject.Provider

fun provideMockVmMap(): MutableMap<Class<out ViewModel>, Provider<ViewModel>> {
    val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>> = mutableMapOf()
    viewModels[HomeViewModel::class.java] = Provider { MockHomeViewModel.getMockHomeViewModel() }
    viewModels[ActorDetailViewModel::class.java] = Provider { MockActorDetailViewModel.getMockActorDetailViewModel() }
    return viewModels
}