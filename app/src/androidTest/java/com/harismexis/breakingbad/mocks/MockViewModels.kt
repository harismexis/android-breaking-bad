package com.harismexis.breakingbad.mocks

import androidx.lifecycle.ViewModel
import com.harismexis.breakingbad.presentation.screens.actordetail.viewmodel.ActorDetailViewModel
import com.harismexis.breakingbad.presentation.screens.home.viewmodel.HomeViewModel
import io.mockk.mockk
import javax.inject.Provider

val mockHomeViewModel: HomeViewModel = mockk(relaxed = true)
val mockActorDetailViewModel: ActorDetailViewModel = mockk(relaxed = true)

fun createMockVmMap(): MutableMap<Class<out ViewModel>, Provider<ViewModel>> {
    val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>> = mutableMapOf()
    viewModels[HomeViewModel::class.java] = Provider { mockHomeViewModel }
    viewModels[ActorDetailViewModel::class.java] = Provider { mockActorDetailViewModel }
    return viewModels
}
