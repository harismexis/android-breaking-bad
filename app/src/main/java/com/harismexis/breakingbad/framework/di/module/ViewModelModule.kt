package com.harismexis.breakingbad.framework.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harismexis.breakingbad.presentation.screens.actordetail.viewmodel.ActorDetailViewModel
import com.harismexis.breakingbad.presentation.screens.deaths.viewmodel.DeathsViewModel
import com.harismexis.breakingbad.presentation.screens.episodes.viewmodel.EpisodesViewModel
import com.harismexis.breakingbad.presentation.screens.home.viewmodel.HomeViewModel
import com.harismexis.breakingbad.presentation.screens.player.viewmodel.PlayerSharedViewModel
import com.harismexis.breakingbad.presentation.screens.quotes.viewmodel.QuotesViewModel
import com.harismexis.breakingbad.presentation.vmfactory.ViewModelFactory
import com.harismexis.breakingbad.presentation.vmfactory.assisted.HomeViewModelFactory
import com.harismexis.breakingbad.presentation.vmfactory.assisted.ViewModelAssistedFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindHomeViewModelFactory(factory: HomeViewModelFactory)
            : ViewModelAssistedFactory<HomeViewModel>

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory)
            : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ActorDetailViewModel::class)
    internal abstract fun actorDetailViewModel(viewModel: ActorDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(QuotesViewModel::class)
    internal abstract fun quotesViewModel(viewModel: QuotesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DeathsViewModel::class)
    internal abstract fun deathsViewModel(viewModel: DeathsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EpisodesViewModel::class)
    internal abstract fun episodesViewModel(viewModel: EpisodesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PlayerSharedViewModel::class)
    internal abstract fun videosViewModel(viewModel: PlayerSharedViewModel): ViewModel
}