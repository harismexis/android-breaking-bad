package com.harismexis.breakingbad.presentation.vmfactory.assisted

import androidx.lifecycle.SavedStateHandle
import com.harismexis.breakingbad.core.repository.actor.ActorsLocal
import com.harismexis.breakingbad.core.repository.actor.ActorsRemote
import com.harismexis.breakingbad.presentation.screens.home.viewmodel.HomeViewModel
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(
    private val actorRemote: ActorsRemote,
    private val actorLocal: ActorsLocal,
) : ViewModelAssistedFactory<HomeViewModel> {
    override fun create(handle: SavedStateHandle): HomeViewModel =
        HomeViewModel(actorRemote, actorLocal, handle)

}
