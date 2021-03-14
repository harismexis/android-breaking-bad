package com.example.breakingbad.framework.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.breakingbad.presentation.screens.actordetail.viewmodel.ActorDetailViewModel
import com.example.breakingbad.presentation.screens.deaths.viewmodel.DeathsViewModel
import com.example.breakingbad.presentation.screens.episodes.viewmodel.EpisodesViewModel
import com.example.breakingbad.presentation.screens.home.viewmodel.HomeViewModel
import com.example.breakingbad.presentation.screens.quotes.viewmodel.QuotesViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        viewModels[modelClass]?.get() as T
}

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
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun homeViewModel(viewModel: HomeViewModel): ViewModel

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
}